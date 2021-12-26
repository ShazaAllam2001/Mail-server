package com.example.Emailserver.UsersAndMails;

import java.util.ArrayList;
public class MessageHeader {
    private String sender;
    private ArrayList<String> reciever;
    private String subject;
    private String folderName;
    private boolean priority;

    public MessageHeader(String sender, ArrayList<String> reciever, String subject,String folderName ,boolean priority){
        this.sender = sender;
        this.reciever = reciever;
        this.subject = subject;
        this.folderName=folderName;
        this.priority=priority;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public ArrayList<String> getReciever() {
        return reciever;
    }

    public void setReciever(ArrayList<String> reciever) {
        this.reciever = reciever;
    }

    public String getSubject() {
        return subject;
    }


    public String getFolderName() {
        return folderName;
    }


    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

 //   @Override

}