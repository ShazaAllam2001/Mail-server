package com.example.Emailserver.UsersAndMails.Mail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MailImmutable extends Mail {

    public MailImmutable() {
        this.mutable = false;
    }

    @Override
    public boolean setID() {
        if(this.ID == 0) {
            this.ID = hashCode();
            return true;
        }
        return false;
    }

    @Override
    public boolean setSender(String sender) {
        if(this.sender == null) {
            this.sender = sender;
            return true;
        }
        return false;
    }

    @Override
    public boolean setReceivers(List<String> receivers) {
        if(this.receivers == null) {
            this.receivers = receivers;
            return true;
        }
        return false;
    }

    @Override
    public boolean setSubject(String subject) {
        if(this.subject == null) {
            this.subject = subject;
            return true;
        }
        return false;
    }

    @Override
    public boolean setCurrTime() {
        if(this.time == null) {
            this.time = new Date(System.currentTimeMillis());
            return true;
        }
        return false;
    }

    @Override
    public boolean setTime(String dateString) throws ParseException {
        if(this.time == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            sdf.setTimeZone(TimeZone.getTimeZone("CET"));
            Date date = sdf.parse(dateString);
            this.time = date;
            return true;
        }
        return false;
    }

    @Override
    public boolean setPriority(Priority priority) {
        if(this.priority == null) {
            this.priority = priority;
            return true;
        }
        return false;
    }

    @Override
    public boolean setBody(String body) {
        if(this.body == null)
            this.body = body;
        return false;
    }

    @Override
    public boolean setAttaches(List<Attachment> attaches) {
        if(this.attaches == null) {
            this.attaches = attaches;
            return true;
        }
        return false;
    }

}
