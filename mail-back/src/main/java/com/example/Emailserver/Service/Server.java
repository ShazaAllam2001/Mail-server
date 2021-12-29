package com.example.Emailserver.Service;

import com.example.Emailserver.UsersAndMails.Contact.Contact;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Draft;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Sent;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Trash;
import com.example.Emailserver.UsersAndMails.User.IUser;
import com.example.Emailserver.UsersAndMails.User.User;
import com.example.Emailserver.Controller.*;
import com.example.Emailserver.library.doubleLinkedList;
import org.json.simple.parser.ParseException;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Server {
    private IUser user;
    private final SaveAndLoad save = new SaveAndLoad();
    private static final Server server = new Server();

    private Server() { }
    public static Server getServer() {
        return server;
    }

    // log in
    public boolean logIn(String Email, String password) throws IOException, ParseException, java.text.ParseException {
        save.makeFirstDirectory();
        Login loginClass=new Login(Email,password);
        IUser userInterface = loginClass.ExistOrNot();

       // check user existence
        if(userInterface ==null) {
            return false;
        } else {
            user = new User(userInterface.getUserName(), userInterface.getPassword(), userInterface.getEmail());
            fillCurrentUser(user.getEmail());
            AutoDelete();  return true;
        }
    }

    // sign up
    public boolean signUp(UserClass user) throws IOException, ParseException {
        save.makeFirstDirectory();
        signUp signUp=new signUp();
        this.user = user;
        return signUp.addUser(user);
    }

    public void fillCurrentUser(String Email ) throws  IOException, ParseException {
        ArrayList<MessageCreator> sent= save.readMessages(Email, Constants.SENT);
        ArrayList<MessageCreator> Inbox= save.readMessages(Email,Constants.INBOX);
        ArrayList<MessageCreator> draft= save.readMessages(Email,Constants.DRAFT);
        ArrayList<MessageCreator> trash= save.readMessages(Email,Constants.TRASH);
        ArrayList<Contact> contact= save.readContactsFromJson(user.getEmail());
        if(Inbox!=null) {
            for (MessageCreator inbox : Inbox) {
                user.addInboxMessage((com.example.Emailserver.UsersAndMails.Mail.MailTypes.Inbox) inbox.buildInboxMessage());}}

        if(sent!=null) {
            for (MessageCreator messageCreator : sent) {
                user.addsentMessage((Sent) messageCreator.buildSentMessage());}}

        if(draft!=null) {
            for (MessageCreator messageCreator : draft) {
                user.addDraftMessage((Draft) messageCreator.buildDraftMessage());} }

        if (trash !=null){
            for (MessageCreator messageCreator : trash) {
                user.addTrashMessage((Trash) messageCreator.builTrashMessage());} }

        if(contact!=null){
            for (Contact value : contact) {
                user.addContact(value);}}
    }

 //function to check user existence
    private boolean exist_user(String Email) throws FileNotFoundException, IOException, ParseException{
        // array of users
        ArrayList<User> ExistUser;
        ExistUser=save.readUsersFromJson();
        for (User value : ExistUser) {
            if (Email.equals(value.getEmail())) {
                return true;} }
        return false;
    }


    public boolean createMessage(String subject , String body , ArrayList<MultipartFile> attaches, ArrayList<String> reciver ,
                                  String sentOrDarft,boolean priority) throws IOException, ParseException{
        if(sentOrDarft.equals(Constants.SENT) ) {
            for (String s : reciver) {
                // check receivers existence
                if (!exist_user(s) || user.getEmail().equals(s)) {
                    return false;
                }}
        }

        SaveAndLoad saveAndLoad =new SaveAndLoad();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        MessageHeader header = new MessageHeader(user.getEmail(),reciver,subject,sentOrDarft,priority);
        MessageBody Body = new MessageBody(body);
        Attachments Attaches;
        MessageCreator myMessage = null;
        if (sentOrDarft.equals(Constants.SENT)) {
            ArrayList<String> attachementsDealing =
                    saveMultipartFile(attaches,user.getEmail(),Constants.ATTACHMENTS_SENT,
                            saveAndLoad.getMessageID(user.getEmail(),Constants.SENT ));
            Attaches = new Attachments(attachementsDealing);
            myMessage = new MessageCreator(header,Body,Attaches,dtf.format(now).toString().toString(),
                    saveAndLoad.getMessageID(user.getEmail(),Constants.SENT ),priority);

            saveAndLoad.sendMessage(myMessage, Constants.SENT, myMessage.getHeader().getSender());

            for (int i = 0; i < reciver.size(); i++) {
                attachementsDealing=saveMultipartFile(attaches,myMessage.getHeader().getReciever().get(i),
                        Constants.ATTACHMENTS_INBOX,saveAndLoad.getMessageID(myMessage.getHeader().getReciever().get(i), Constants.INBOX));
                Attaches = new Attachments(attachementsDealing);
                header = new MessageHeader(user.getEmail(),reciver,subject,Constants.INBOX,priority);
                myMessage = new MessageCreator(header,Body,Attaches,dtf.format(now).toString().toString(),
                        saveAndLoad.getMessageID(myMessage.getHeader().getReciever().get(i), Constants.INBOX),priority);
                saveAndLoad.sendMessage(myMessage, Constants.INBOX, myMessage.getHeader().getReciever().get(i));
            }
        }

        if (sentOrDarft.equals(Constants.SENT))
            user.addsentMessage((Sent) myMessage.buildSentMessage());
        else
            user.addDraftMessage((Draft) myMessage.buildDraftMessage());
        return true;
    }


    public void sendToTrash(String folder, int messageID) throws  IOException, ParseException {

        ArrayList<MessageCreator> previousMessage=save.readMessages(user.getEmail(), folder);
        MessageCreator messageWeWant =null;
        for(int i=0;i<previousMessage.size();i++) {
            if(previousMessage.get(i).getID()==messageID) {
                messageWeWant=previousMessage.get(i);
                previousMessage.remove(i);
            }
            if(messageWeWant==null)return;
            save.sendMessage(messageWeWant, Constants.TRASH, user.getEmail());
        }
        save.ClearFileContent(user.getEmail(), folder);
        for (MessageCreator messageCreator : previousMessage) {
            save.sendMessage(messageCreator, folder, user.getEmail());
        }
    }

    public void restoreFromTrash(int messageID) throws IOException, ParseException {

        ArrayList<MessageCreator> previousMessage=save.readMessages(user.getEmail(), Constants.TRASH);
        MessageCreator messageWeWant =null;
        for(int i=0;i<previousMessage.size();i++) {
            if(previousMessage.get(i).getID()==messageID) {
                messageWeWant=previousMessage.get(i);
                previousMessage.remove(i);
            }
            if(messageWeWant==null)return;
            save.sendMessage(messageWeWant, messageWeWant.getHeader().getFolderName(), user.getEmail());
        }
        save.ClearFileContent(user.getEmail(), Constants.TRASH);
        for (MessageCreator messageCreator : previousMessage) {
            save.sendMessage(messageCreator, Constants.TRASH, user.getEmail());
        }

    }


    public void AutoDelete() throws java.text.ParseException, IOException, ParseException {

        ArrayList<MessageCreator> messages;
        try {
            messages=save.readMessages(user.getEmail(), Constants.TRASH);
            if(messages == null)
                return;}
        catch (Exception e) {
            return ;}
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date secondDate = sdf.parse(dtf.format(now));
        for(int i=0;i<messages.size();i++) {
            Date firstDate=new SimpleDateFormat("dd/MM/yyyy").parse(messages.get(i).getTime());
            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            if(diffInMillies>30) {
                messages.remove(i);
            }
        }
        save.ClearFileContent(user.getEmail(), Constants.TRASH);
        for (MessageCreator message : messages) {
            save.sendMessage(message, Constants.TRASH, user.getEmail());}
    }

    public  ArrayList<String> saveMultipartFile(ArrayList<MultipartFile> multipartFiles, String Email,String folder,int messageID) throws IOException {
        if (multipartFiles == null) {return null; }
        ArrayList<String>files = new ArrayList<>();
        String makemessageAttachementsFolder=Constants.DATABASE_PATH+Email+"//"+Constants.ATTACHMENTS +"//"+folder +messageID;//directory
        File file = new File(makemessageAttachementsFolder);
        file.mkdir();
        for(MultipartFile multipartFile : multipartFiles) {
            String directory= makemessageAttachementsFolder+"//" + multipartFile.getOriginalFilename();
            Path path = Path.of(directory);
            Files.copy(multipartFile.getInputStream() ,path, StandardCopyOption.REPLACE_EXISTING);
            files.add(directory);
        }
        return files;}

    public ArrayList<MessageCreator> getMails(String type) throws IOException, ParseException {
        return save.readMessages(user.getEmail() , type);}

    public doubleLinkedList putMessagesInDoubleLinkedList(ArrayList<MessageCreator> messages) {
        doubleLinkedList db=new doubleLinkedList();
        for (MessageCreator message : messages) {
            db.add(message);
        }
        return db;}

    public ArrayList<MessageCreator> putMessagesInArrayList(doubleLinkedList messages) {
        ArrayList<MessageCreator> arrayListMessages=new ArrayList<>();
        for(int i=0;i<messages.size();i++) {
            arrayListMessages.add((MessageCreator) messages.get(i));}
        return arrayListMessages;}

    public String getUserEmail(){
        return user.getEmail();
    }
    public String getUserName(){
        return user.getUserName();
    }
    public ArrayList<? extends Message> Filter (String  filterName , String filterBy , String place ){
        ArrayList<? extends Message> result =new ArrayList<>() ;
        switch (filterBy){
            case Constants.SUBJECT:
                Criteria subjectFilter = new SubjectCriteria(filterName);
                result = getMessages(place, result, subjectFilter);

                break;
            case Constants.RECEIVERS:
                Criteria reciverFilter = new receiversCriteria(filterName);
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
                Criteria PriorityCriteria = new PriorityCriteria(check);
                result = getMessages(place, result, PriorityCriteria);
                break;
        }

        return result;
    }

    private ArrayList<? extends Message> getMessages(String subjectOrReceiversOrBodyOrDateOrPriority, ArrayList<? extends Message> result, Criteria subjectFilter) {
        switch (subjectOrReceiversOrBodyOrDateOrPriority) {
            case Constants.INBOX:
                result = subjectFilter.filterCriteria(user.getInbox());
                break;
            case Constants.SENT:
                result = subjectFilter.filterCriteria(user.getSentMessage());
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

}