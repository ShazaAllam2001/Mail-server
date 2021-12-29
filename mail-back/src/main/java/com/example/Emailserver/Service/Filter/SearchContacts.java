package com.example.Emailserver.Service.Filter;

import com.example.Emailserver.UsersAndMails.Contact.IContact;

import java.util.ArrayList;
import java.util.List;

public class SearchContacts {

    List<IContact> contacts;

    public SearchContacts(List<IContact> contacts) {
        this.contacts = contacts;
    }

    public List<IContact> searchByName(String name){
        name = name.toLowerCase();
        List<IContact> contactsResults = new ArrayList<>();
        for (IContact contact : contacts){
            if ((contact.getName().toLowerCase()).contains(name)){
                contactsResults.add(contact);
            }
        }
        return contactsResults;
    }

    public ArrayList<IContact> searchingByEmails(String Email){
        if(contacts == null)
            contacts = new ArrayList<>();
        Email = Email.toLowerCase();
        ArrayList<IContact> contactsResults = new ArrayList<>();
        for (IContact contact : contacts){
            for (String email : contact.getEmails())
                if ((email.toLowerCase()).contains(Email)){
                    contactsResults.add(contact);
                }
        }
        return contactsResults;
    }
}
