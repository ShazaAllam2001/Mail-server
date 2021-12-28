package com.example.Emailserver.UsersAndMails.Mail;

import com.example.Emailserver.UsersAndMails.User.IUser;

import java.util.Date;
import java.util.List;

public abstract class Mail {
    protected int ID;
    protected IUser sender;
    protected List<IUser> receivers;
    protected String subject;
    protected Date time;
    protected Priority priority;
    protected String path;
    protected String body;
    protected List<Attachment> attaches;

    public int getID() { return ID; }
    public abstract boolean setID(int ID);

    public String getPath() { return path; }
    public abstract boolean setPath(String path);

    public IUser getSender() {
        return sender;
    }
    public abstract boolean setSender(IUser sender);

    public List<IUser> getReceivers() {
        return receivers;
    }
    public abstract boolean setReceivers(List<IUser> receivers);

    public String getSubject() {
        return subject;
    }
    public abstract boolean setSubject(String subject);

    public Date getTime() { return time; }
    public abstract boolean setTime(Date time);

    public int getPriorityLevel() {
        return priority.level;
    }
    public abstract boolean setPriority(Priority priority);

    public String getBody() { return body; }
    public abstract boolean setBody(String body);

    public List<Attachment> getAttaches() { return attaches; }
    public abstract boolean setAttaches(List<Attachment> attaches);

}
