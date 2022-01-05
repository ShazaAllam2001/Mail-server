package com.example.Emailserver.Service;

import com.example.Emailserver.Service.Filter.Mail.*;
import com.example.Emailserver.Service.LoadAndSave.Load;
import com.example.Emailserver.Service.LoadAndSave.Save;
import com.example.Emailserver.UsersAndMails.Contact.IContact;
import com.example.Emailserver.UsersAndMails.Mail.Attachment;
import com.example.Emailserver.UsersAndMails.Mail.Mail;
import com.example.Emailserver.UsersAndMails.User.IUser;
import com.example.Emailserver.UsersAndMails.User.User;
import com.example.Emailserver.Controller.*;
import com.example.Emailserver.library.doubleLinkedList;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Server {
    private IUser user;
    private MultipartFile[] attachments;
    private final Load load = new Load();
    private final Save save = new Save();
    private static final Server server = new Server();

    private Server() { }
    public static Server getServer() {
        return server;
    }

    public boolean signUp(String userName, String email, String password) throws JSONException {
        Proxy signup = new Proxy(email,password);
        IUser user = signup.ExistOrNot();
        // check user existence
        if(user == null) {
            this.user = new User(userName, email, password);
            save.saveUser(this.user);
            return true;
        } else {
            return false;
        }
    }

    public String signIn(String email, String password) throws java.text.ParseException, JSONException, IOException {
        Proxy login = new Proxy(email,password);
        IUser user = login.ExistOrNot();
        // check user existence
        if(user == null) {
            return "notFound";
        } else {
            this.user = user;
            if(password.equals(user.getPassword())) {
                fillCurrentUser(user.getEmail());
                AutoDelete();
                return "success";
            }
            return "mismatch";
        }
    }

    public boolean signOut() {
        user = null;
        return true;
    }

    private void fillCurrentUser(String Email) {
        List<Mail> sent = load.loadUserMails(Email,Constants.SENT);
        List<Mail>inbox = load.loadUserMails(Email,Constants.INBOX);
        List<Mail> draft = load.loadUserMails(Email,Constants.DRAFT);
        List<Mail> trash = load.loadUserMails(Email,Constants.TRASH);
        if(sent!=null)
            this.user.setSent(sent);
        if(inbox!=null)
            this.user.setInbox(inbox);
        if(draft!=null)
            this.user.setDraft(draft);
        if(trash !=null)
            this.user.setTrash(trash);
    }

    public void AutoDelete() throws java.text.ParseException, IOException {
        if(this.user.getTrash() == null)
            return;
        Date firstDate = new Date();
        Date secondDate;
        for(int i=0;i<this.user.getTrash().size();i++) {
            secondDate = this.user.getTrash().get(i).getTime();
            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            if(diffInMillies > 30) {
               this.user.getTrash().remove(i);
            }
        }
        save.ClearFileContent(user.getEmail(), Constants.TRASH);
    }

    public boolean createMessage(JSONObject email) throws JSONException, java.text.ParseException, IOException {
        String folder = email.getString("folder");
        Load load = new Load();
        Mail mail = load.JSONToMail(folder,email);
        // save to JSON file
        Save save = new Save();
        save.saveEmail(mail, mail.getSender(), folder);
        saveMultipartFile(mail,folder);
        this.user.addToFolder(folder, mail);
        if(mail.getReceivers()!=null && (folder.compareToIgnoreCase(Constants.SENT)==0)) {
            for(String receiver : mail.getReceivers()) {
                // check receivers existence
                if(exist_user(receiver)) {
                    save.saveEmail(mail, receiver, "Inbox");
                    saveMultipartFile(mail,"Inbox");
                }
            }
        }
        return true;
    }

    public void setAttachments(MultipartFile[] attachments) {
        this.attachments = attachments;
    }

    public MultipartFile[] getAttachments() {
        return attachments;
    }

    //function to check user existence
    private boolean exist_user(String Email) throws JSONException {
        // array of users
        IUser ExistUser = load.loadUserData(Email);
        if(ExistUser!=null && Email.equals(ExistUser.getEmail())) {
            return true;
        }
        return false;
    }

    public List<IContact> getContacts() {
        return user.getContacts();
    }

    public void sendToTrash(String folder, int messageID) throws JSONException, IOException {
        List<Mail> mails = this.user.getFolderMails(folder);
        // search for mail with messageID
        Mail mail = null;
        for(Mail m : mails) {
            if(m.getID() == messageID) {
                mail = m;
                // remove from the source file
                this.user.removeFromFolder(folder,m);
                break;
            }
        }
        // save to Trash JSON file
        Save save = new Save();
        save.saveEmail(mail, mail.getSender(),Constants.TRASH);
        this.user.addTrashMail(mail);
    }

    public void restoreFromTrash(int messageID) throws JSONException, IOException {
        List<Mail> mails = this.user.getTrash();
        // search for mail with messageID
        Mail mail = null;
        for(Mail m : mails) {
            if(m.getID() == messageID) {
                mail = m;
                // remove from the source file
                this.user.removeTrashMail(m);
                break;
            }
        }
        // save to Trash JSON file
        Save save = new Save();
        save.saveEmail(mail, mail.getSender(),Constants.DRAFT);////
        this.user.addTrashMail(mail);
    }

    public boolean saveMultipartFile(Mail mail, String folder) {
        return save.saveAttachments(attachments, mail, mail.getSender(), folder);
    }

    public List<Mail> getMails(String type) throws IOException, ParseException {
        return load.loadUserMails(user.getEmail() , type);
    }

    public doubleLinkedList putMessagesInDoubleLinkedList(ArrayList<Mail> messages) {
        doubleLinkedList db = new doubleLinkedList();
        for (Mail message : messages) {
            db.add(message);
        }
        return db;
    }

    public String getUserEmail(){
        return user.getEmail();
    }
    public String getUserName(){
        return user.getUserName();
    }

    public List<? extends Mail> Filter (String  filterName , String filterBy , String place ){
        List<? extends Mail> result =new ArrayList<>() ;
        switch (filterBy){
            case Constants.SUBJECT:
                Criteria subjectFilter = new SubjectCriteria(filterName);
                result = getMessages(place, result, subjectFilter);

                break;
            case Constants.RECEIVERS:
                Criteria reciverFilter = new ReceiversCriteria(filterName);
                result = getMessages(place, result, reciverFilter);
                break;
            case Constants.BODY:
                Criteria bodyFilter = new BodyCriteria(filterName);
                result = getMessages(place, result, bodyFilter);
                break;
            case Constants.DATE:
                Criteria dateFilter = new DateCriteria(filterName);
                result = getMessages(place, result, dateFilter);
                break;
            case Constants.PRIORITY:
                boolean check;
                check= filterName.equals(Constants.TRUE);
                //Criteria PriorityCriteria = new PriorityCriteria(check);
                //result = getMessages(place, result, PriorityCriteria);
                break;
        }
        return result;
    }

    private List<? extends Mail> getMessages(String subjectOrReceiversOrBodyOrDateOrPriority, List<? extends Mail> result, Criteria subjectFilter) {
        switch (subjectOrReceiversOrBodyOrDateOrPriority) {
            case Constants.INBOX:
                result = subjectFilter.filterCriteria(user.getInbox());
                break;
            case Constants.SENT:
                result = subjectFilter.filterCriteria(user.getSent());
                break;
            case Constants.TRASH:
                result = subjectFilter.filterCriteria(user.getTrash());
                break;
            case Constants.DRAFT:
                result = subjectFilter.filterCriteria(user.getDraft());
                break;
        }
        return result;
    }

}
