package com.example.Emailserver.controller;

import com.example.Emailserver.UsersAndMails.Contact;

import java.util.ArrayList;

public class SearchingContacts {

    ArrayList<Contact> contacts;

    public SearchingContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<Contact> searchingByName(String name){
        name = name.toLowerCase();
        ArrayList<Contact> contactsResults = new ArrayList<>();
        for (Contact contact : contacts){
            if ((contact.getName().toLowerCase()).contains(name)){
                contactsResults.add(contact);
            }
        }
        return contactsResults;
    }

    public ArrayList<Contact> searchingByEmails(String Email){
        if(contacts == null)
            contacts = new ArrayList<>();
        Email = Email.toLowerCase();
        ArrayList<Contact> contactsResults = new ArrayList<>();
        for (Contact contact : contacts){
            for (String email : contact.getEmails())
            if ((email.toLowerCase()).contains(Email)){
                contactsResults.add(contact);
            }
        }
        return contactsResults;
    }
}
