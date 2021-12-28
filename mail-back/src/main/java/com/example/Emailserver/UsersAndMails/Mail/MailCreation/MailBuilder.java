package com.example.Emailserver.UsersAndMails.Mail.MailCreation;

import com.example.Emailserver.UsersAndMails.Mail.Attachment;
import com.example.Emailserver.UsersAndMails.Mail.Mail;
import com.example.Emailserver.UsersAndMails.Mail.Priority;
import com.example.Emailserver.UsersAndMails.User.IUser;

import java.util.Date;
import java.util.List;

public class MailBuilder {
    private MailFactory factory= new MailFactory();
    private Mail mail;

    public MailBuilder(String mailType) {
        this.mail = factory.getMail(mailType);
    }

    public Mail setID(int ID) {
        if(this.mail.setID(ID))
            return this.mail;
        return null;
    }

    public Mail setPath(String path) {
        if(this.mail.setPath(path))
            return this.mail;
        return null;
    }

    public Mail setSender(IUser sender) {
        if(this.mail.setSender(sender))
            return this.mail;
        return null;
    }

    public Mail setReceivers(List<IUser> receivers) {
        if(this.mail.setReceivers(receivers))
            return this.mail;
        return null;
    }

    public Mail setSubject(String subject) {
        if(this.mail.setSubject(subject))
            return this.mail;
        return null;
    }

    public Mail setTime(Date time) {
        if(this.mail.setTime(time))
            return this.mail;
        return null;
    }

    public Mail setPriority(Priority priority) {
        if(this.mail.setPriority(priority))
            return this.mail;
        return null;
    }

    public Mail setBody(String body) {
        if(this.mail.setBody(body))
            return this.mail;
        return null;
    }

    public Mail setAttaches(List<Attachment> attaches) {
        if(this.mail.setAttaches(attaches))
            return this.mail;
        return null;
    }

}