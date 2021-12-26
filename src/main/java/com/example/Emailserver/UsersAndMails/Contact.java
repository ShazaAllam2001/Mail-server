package com.example.Emailserver.UsersAndMails;

import java.util.ArrayList;

public class Contact {
    private String name;
    private ArrayList<String> emails;

    public Contact(String name, ArrayList<String> emails) {
        this.name = name;
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

   // @Override

}
