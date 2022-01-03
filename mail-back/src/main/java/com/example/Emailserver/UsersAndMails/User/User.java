package com.example.Emailserver.UsersAndMails.User;

import com.example.Emailserver.Service.Constants;
import com.example.Emailserver.UsersAndMails.Contact.IContact;
import com.example.Emailserver.UsersAndMails.Mail.Mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class User implements IUser {
    private String userName;
    private String password;
    private final String email;
    private List<IContact> contacts;
    private List<Mail> sent;
    private List<Mail> inbox;
    private List<Mail> draft;
    private List<Mail> trash;
    private HashMap<String,List<Mail>> custom = new HashMap<>();

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() { return userName; }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public List<IContact> getContacts() {
        return contacts;
    }
    public void setContacts(List<IContact> contacts) { this.contacts = contacts; }
    public void addContact(IContact contact){
        if(contacts == null)
            contacts = new ArrayList<>();
        contacts.add(contact);
    }
    public void removeContact(IContact contact){
        contacts.remove(contact);
    }

    public List<Mail> getSent() {
        return sent;
    }
    public void setSent(List<Mail> sent) { this.sent = sent; }
    public void addSentMail(Mail mail){
        if(sent == null)
            sent = new LinkedList<>();
        sent.add(mail);
    }
    public void removeSentMail(Mail mail){
        sent.remove(mail);
    }

    public List<Mail> getInbox() {
        return inbox;
    }
    public void setInbox(List<Mail> inbox) {
        this.inbox = inbox;
    }
    public void addInboxMail(Mail mail){
        if(inbox == null)
            inbox = new LinkedList<>();
        inbox.add(mail);
    }
    public void removeInboxMail(Mail mail){
        inbox.remove(mail);
    }

    public List<Mail> getDraft() {
        return draft;
    }
    public void setDraft(List<Mail> draft) {
        this.draft = draft;
    }
    public void addDraftMail(Mail mail){
        if(draft == null)
            draft = new LinkedList<>();
        draft.add(mail);
    }
    public void removeDraftMail(Mail mail){
        draft.remove(mail);
    }

    public List<Mail> getTrash() {
        return trash;
    }
    public void setTrash(List<Mail> trash) {
        this.trash = trash;
    }
    public void addTrashMail(Mail mail){
        if(trash == null)
            trash = new LinkedList<>();
        trash.add(mail);
    }
    public void removeTrashMail(Mail mail){
        trash.remove(mail);
    }

    @Override
    public void addFolder(String name) {
        if(!this.custom.containsKey(name))
           this.custom.put(name,new LinkedList<>());
        else
            throw new RuntimeException("This file name exists!");
    }

    @Override
    public void removeFolder(String name) {
        if(this.custom.containsKey(name))
            this.custom.remove(name);
        else
            throw new RuntimeException("This file name does not exist!");
    }

    @Override
    public void renameFolder(String name, String newName) {
        if(this.custom.containsKey(name)) {
            List<Mail> mail = this.custom.get(name);
            this.custom.remove(name);
            this.custom.put(newName,mail);
        } else {
            throw new RuntimeException("This file name does not exist!");
        }
    }

    @Override
    public void addToFolder(String name, Mail mail) {
        if(name.equals(Constants.SENT)) {
            this.sent.add(mail);
        } else if(name.equals(Constants.INBOX)) {
            this.inbox.add(mail);
        } else if(name.equals(Constants.DRAFT)) {
            this.draft.add(mail);
        } else if(name.equals(Constants.TRASH)) {
            this.trash.add(mail);
        } else if(this.custom.containsKey(name)) {
            this.custom.get(name).add(mail);
        } else {
            throw new RuntimeException("This file name does not exist!");
        }
    }

    @Override
    public void removeFromFolder(String name, Mail mail) {
        if(name.equals(Constants.SENT)) {
            this.sent.remove(mail);
        } else if(name.equals(Constants.INBOX)) {
            this.inbox.remove(mail);
        } else if(name.equals(Constants.DRAFT)) {
            this.draft.remove(mail);
        } else if(name.equals(Constants.TRASH)) {
            this.trash.remove(mail);
        } else if(this.custom.containsKey(name)) {
            this.custom.get(name).remove(mail);
        } else {
            throw new RuntimeException("This file name does not exist!");
        }
    }

    @Override
    public List<Mail> getFolderMails(String name) {
        if(name.equals(Constants.SENT)) {
            return this.sent;
        } else if(name.equals(Constants.INBOX)) {
            return this.inbox;
        } else if(name.equals(Constants.DRAFT)) {
            return this.draft;
        } else if(name.equals(Constants.TRASH)) {
            return this.trash;
        } else if(this.custom.containsKey(name)) {
            return this.custom.get(name);
        } else {
            throw new RuntimeException("This file name does not exist!");
        }
    }

}