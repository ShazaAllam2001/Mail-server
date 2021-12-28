package com.example.Emailserver.UsersAndMails;

import java.util.ArrayList;
public class MessageHeader {
    private String sender;
    private ArrayList<String> receiver;
    private String subject;
    private String folderName;
    private boolean priority;

    public MessageHeader(String sender, ArrayList<String> receiver, String subject,String folderName ,boolean priority){
        this.sender = sender;
        this.receiver = receiver;
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

    public ArrayList<String> getReceiver() {
        return receiver;
    }

    public void setReceiver(ArrayList<String> receiver) {
        this.receiver = receiver;
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