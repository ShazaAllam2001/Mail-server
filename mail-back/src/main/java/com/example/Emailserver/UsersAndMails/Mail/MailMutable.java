package com.example.Emailserver.UsersAndMails.Mail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MailMutable extends Mail {
    private boolean mutable;

    public MailMutable() {
        this.mutable = true;
    }

    @Override
    public boolean isMutable() { return mutable; }

    @Override
    public boolean setID() {
        this.ID = hashCode();
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
    public boolean setCurrTime() {
        this.time = new Date(System.currentTimeMillis());
        return true;
    }

    @Override
    public boolean setTime(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        Date date = sdf.parse(dateString);
        this.time = date;
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
