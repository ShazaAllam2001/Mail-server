package com.example.Emailserver.UsersAndMails.Mail;

import com.example.Emailserver.UsersAndMails.User.IUser;

import java.util.Date;
import java.util.List;

public class MailImmutable extends Mail {

    public MailImmutable() { }

    @Override
    public boolean setID(int ID) {
        if(this.ID == 0) {
            this.ID = ID;
            return true;
        }
        return false;
    }

    @Override
    public boolean setPath(String path) {
        this.path = path;
        return true;
    }

    @Override
    public boolean setSender(IUser sender) {
        if(this.sender == null) {
            this.sender = sender;
            return true;
        }
        return false;
    }

    @Override
    public boolean setReceivers(List<IUser> receivers) {
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
    public boolean setTime(Date time) {
        if(this.time == null) {
            this.time = time;
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
