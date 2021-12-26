package com.example.Emailserver.UsersAndMails;

public class Sent extends Message {

    public Sent(int iD, MessageBody body, MessageHeader header, Attachments attaches, String time , boolean priority) {
        super(iD, body, header, attaches, time , priority);

    }

}