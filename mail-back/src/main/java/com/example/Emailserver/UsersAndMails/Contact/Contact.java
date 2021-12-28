package com.example.Emailserver.UsersAndMails.Contact;

import java.util.List;

public class Contact implements IContact {
    private String name;
    private List<String> emails;

    public Contact(String name, List<String> emails) {
        this.name = name;
        if(emails.size()>0)
           this.emails = emails;
        else
            throw new RuntimeException("No emails are entered!");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEmails() {
        return emails;
    }
    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public void addEmail(String email) { this.emails.add(email); }
    public void removeEmail(String email) { this.emails.remove(email); }

}
