package com.example.Emailserver.Service.Sort;

import com.example.Emailserver.UsersAndMails.Contact.IContact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortContacts {

    private List<IContact> contacts;

    public SortContacts(List<IContact> contacts) {
        this.contacts = contacts;
    }

    public List<IContact> sortByName() {
        List<IContact> contacts = new ArrayList<>();
        Collections.copy(contacts, this.contacts);
        Collections.sort(contacts, Comparator.comparing(IContact::getName));
        return contacts;
    }

    // sorts by the first email in the email list
    public List<IContact> sortByEmail() {
        List<IContact> contacts = new ArrayList<>();
        Collections.copy(contacts, this.contacts);
        Collections.sort(contacts, Comparator.comparing(o -> o.getEmails().get(0)));
        return contacts;
    }


}
