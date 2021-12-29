package com.example.Emailserver.UsersAndMails.Mail.MailCreation;

import com.example.Emailserver.UsersAndMails.Mail.Attachment;
import com.example.Emailserver.UsersAndMails.Mail.Mail;
import com.example.Emailserver.UsersAndMails.Mail.Priority;
import com.example.Emailserver.UsersAndMails.User.IUser;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class MailBuilder {
    private MailFactory factory= new MailFactory();
    private Mail mail;

    public MailBuilder(String mailType) {
        this.mail = factory.getMail(mailType);
    }

    public Mail getMail() {
        return this.mail;
    }

    public MailBuilder setID() {
        this.mail.setID();
        return this;
    }

    public MailBuilder setSender(String sender) {
        this.mail.setSender(sender);
        return this;
    }

    public MailBuilder setReceivers(List<String> receivers) {
        this.mail.setReceivers(receivers);
        return this;
    }

    public MailBuilder setSubject(String subject) {
        this.mail.setSubject(subject);
        return this;
    }

    public MailBuilder setCurrTime() {
        this.mail.setCurrTime();
        return this;
    }

    public MailBuilder setTime(String dateString) throws ParseException {
        this.mail.setTime(dateString);
        return this;
    }

    public MailBuilder setPriority(Priority priority) {
        this.mail.setPriority(priority);
        return this;
    }

    public MailBuilder setBody(String body) {
        this.mail.setBody(body);
        return this;
    }

    public MailBuilder setAttaches(List<Attachment> attaches) {
        this.mail.setAttaches(attaches);
        return this;
    }

}