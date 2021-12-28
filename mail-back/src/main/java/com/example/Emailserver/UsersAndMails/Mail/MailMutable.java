package com.example.Emailserver.UsersAndMails.Mail;

import com.example.Emailserver.UsersAndMails.User.IUser;

import java.util.Date;
import java.util.List;

public class MailMutable extends Mail {
    public final boolean mutable;

    public MailMutable() {
        this.mutable = true;
    }

    @Override
    public boolean setPath(String path) {
        this.path = path;
        return true;
    }

    @Override
    public boolean setSender(String sender) {
        this.sender = sender;
        return true;
    }

    @Override
    public boolean setReceivers(List<String> receivers) {
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
