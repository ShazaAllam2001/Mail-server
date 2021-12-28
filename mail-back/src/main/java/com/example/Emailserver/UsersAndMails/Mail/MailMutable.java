package com.example.Emailserver.UsersAndMails.Mail;

import com.example.Emailserver.UsersAndMails.User.IUser;

import java.util.Date;
import java.util.List;

public class MailMutable extends Mail {

    public MailMutable() { }

    @Override
    public boolean setID(int ID) {
        this.ID = ID;
        return true;
    }

    @Override
    public boolean setPath(String path) {
        this.path = path;
        return true;
    }

    @Override
    public boolean setSender(IUser sender) {
        this.sender = sender;
        return true;
    }

    @Override
    public boolean setReceivers(List<IUser> receivers) {
        this.receivers = receivers;
        return true;
    }

    @Override
    public boolean setSubject(String subject) {
        this.subject = subject;
        return true;
    }

    @Override
    public boolean setTime(Date time) {
        this.time = time;
        return true;
    }

    @Override
    public boolean setPriority(Priority priority) {
        this.priority = priority;
        return true;
    }

    @Override
    public boolean setBody(String body) {
        this.body = body;
        return true;
    }

    @Override
    public boolean setAttaches(List<Attachment> attaches) {
        this.attaches = attaches;
        return true;
    }
    public boolean addAttach(Attachment attach) {
        this.attaches.add(attach);
        return true;
    }
    public boolean removeAttach(Attachment attach) {
        this.attaches.remove(attach);
        return true;
    }

}
