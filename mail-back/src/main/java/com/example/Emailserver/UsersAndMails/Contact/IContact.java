package com.example.Emailserver.UsersAndMails.Contact;

import java.util.List;

public interface IContact {
    public String getName();
    public void setName(String name);

    public List<String> getEmails();
    public void setEmails(List<String> emails);

    public void addEmail(String email);
    public void removeEmail(String email);
}
