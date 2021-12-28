package com.example.Emailserver.UsersAndMails.Contact;

import java.util.LinkedList;
import java.util.List;

public class NullContact implements IContact {
    private String name;
    private List<String> emails;

    public NullContact() {
        this.name = "";
        this.emails = new LinkedList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) { }

    public List<String> getEmails() {
        return emails;
    }
    public void setEmails(List<String> emails) { }

    public void addEmail(String email) { }
    public void removeEmail(String email) { }
}
