package com.example.Emailserver.UsersAndMails.Mail;

import com.example.Emailserver.UsersAndMails.User.IUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

public abstract class Mail {
    protected int ID;
    protected String sender;
    protected List<String> receivers;
    protected String subject;
    protected Date time;
    protected Priority priority;
    protected String body;
    protected List<Attachment> attaches;

    public abstract boolean isMutable();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mail)) return false;
        Mail mail = (Mail) o;
        return getID() == mail.getID()
                && isMutable() == mail.isMutable()
                && getSender().equals(mail.getSender())
                && Objects.equals(getReceivers(), mail.getReceivers())
                && Objects.equals(getSubject(), mail.getSubject())
                && getTime().equals(mail.getTime())
                && priority == mail.priority
                && Objects.equals(getBody(), mail.getBody())
                && Objects.equals(getAttaches(), mail.getAttaches());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSender(), getReceivers(), getSubject(), getTime(), priority, getBody(), getAttaches(), isMutable());
    }

    public int getID() { return ID; }
    public abstract boolean setID();

    public String getSender() {
        return sender;
    }
    public abstract boolean setSender(String sender);

    public List<String> getReceivers() {
        return receivers;
    }
    public abstract boolean setReceivers(List<String> receivers);

    public String getSubject() {
        return subject;
    }
    public abstract boolean setSubject(String subject);

    public Date getTime() { return time; }
    public String getTimeString() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        return sdf.format(date);
    }
    public abstract boolean setCurrTime();
    public abstract boolean setTime(String dateString) throws ParseException;

    public int getPriorityLevel() {
        return priority.level;
    }
    public abstract boolean setPriority(Priority priority);

    public String getBody() { return body; }
    public abstract boolean setBody(String body);

    public List<Attachment> getAttaches() { return attaches; }
    public abstract boolean setAttaches(List<Attachment> attaches);

}
