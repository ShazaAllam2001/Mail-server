package com.example.Emailserver.Server;

import com.example.Emailserver.UsersAndMails.*;
import com.example.Emailserver.controller.*;
import com.example.Emailserver.UsersAndMails.*;
import com.example.Emailserver.controller.*;
import com.example.Emailserver.UsersAndMails.*;
import com.example.Emailserver.controller.*;
import com.example.Emailserver.UsersAndMails.*;
import com.example.Emailserver.controller.*;
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
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class Server {
    private UserClass user;
    private final SaveAndLoad save = new SaveAndLoad();
    private static final Server server = new Server();
    private Server(){}
    public static Server getServer() {
        return server;
    }

    public boolean LOGIN(String Email,String password) throws  IOException, ParseException, java.text.ParseException {
        Login loginClass=new Login(Email,password);
         User userInterface = loginClass.ExistOrNot();

        if(userInterface ==null) {
            return false;
        }else {
            user=new UserClass(userInterface.getUserName(), userInterface.getPassword(), userInterface.getEmail());
            fillCurrentUser(user.getEmail());
            AutoDelete();  return true;
        }
    }

    public boolean signUp(UserClass user) throws IOException, ParseException {
        signUp signUp=new signUp();
        this.user = user;
        return signUp.addUser(user);
    }


    public boolean  createMessage(String subject , String body , ArrayList<MultipartFile> attaches, ArrayList<String> reciver , String sentOrDarft,boolean priority) throws IOException, ParseException{
        if(sentOrDarft.equals(Constants.SENT) ) {
            for (String s : reciver) {
                if (!exist_user(s) || user.getEmail().equals(s)) {
                    return false;
                }
            }
        }

        SaveAndLoad saveAndLoad =new SaveAndLoad();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        MessageHeader header = new MessageHeader(user.getEmail(),reciver,subject,sentOrDarft,priority);
        MessageBody Body = new MessageBody(body);
        Attachments Attaches;
        MessageCreator myMessage ;
        if (sentOrDarft.equals(Constants.SENT)) {
            ArrayList<String> attachementsDealing =
                    saveMultipartFile(attaches,user.getEmail(),Constants.ATTACHMENTS_SENT,saveAndLoad.getMessageID(user.getEmail(),Constants.SENT ));
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
            user.addsentMessage((Sent)myMessage.buildSentMessage());
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
                return;
        }catch (Exception e) {
            return ;
        }
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
            save.sendMessage(message, Constants.TRASH, user.getEmail());
        }

    }

    public ArrayList<MessageCreator> getMails(String type) throws IOException, ParseException {
        return save.readMessages(user.getEmail() , type);
    }

    public doubleLinkedList putMessagesInDoubleLinkedList(ArrayList<MessageCreator> messages) {
        doubleLinkedList db=new doubleLinkedList();
        for (MessageCreator message : messages) {
            db.add(message);
        }
        return db;

    }

    public ArrayList<MessageCreator> putMessagesInArrayList(doubleLinkedList messages) {
        ArrayList<MessageCreator> arrayListMessages=new ArrayList<>();
        for(int i=0;i<messages.size();i++) {
            arrayListMessages.add((MessageCreator) messages.get(i));
        }
        return arrayListMessages;

    }

    public String getUserEmail(){
        return user.getEmail();
    }
    public String getUserName(){
        return user.getUserName();
    }
}