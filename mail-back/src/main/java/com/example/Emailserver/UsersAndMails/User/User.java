package com.example.Emailserver.UsersAndMails.User;

import com.example.Emailserver.UsersAndMails.Contact.IContact;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Draft;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Inbox;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Sent;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Trash;

import java.util.LinkedList;
import java.util.List;

public class User implements IUser {
    private String userName;
    private String password;
    private final String email;
    private List<IContact> contacts;
    private List<Sent> sent;
    private List<Inbox> inbox;
    private List<Draft> draft;
    private List<Trash> trash;

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
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
            contacts = new LinkedList<>();
        contacts.add(contact);
    }
    public void removeContact(IContact contact){
        contacts.remove(contact);
    }

    public List<Sent> getSent() {
        return sent;
    }
    public void setSent(List<Sent> sent) { this.sent = sent; }
    public void addSentMail(Sent mail){
        if(sent == null)
            sent = new LinkedList<>();
        sent.add(mail);
    }
    public void removeSentMail(Sent mail){
        sent.remove(mail);
    }

    public List<Inbox> getInbox() {
        return inbox;
    }
    public void setInbox(List<Inbox> inbox) {
        this.inbox = inbox;
    }
    public void addInboxMail(Inbox mail){
        if(inbox == null)
            inbox = new LinkedList<>();
        inbox.add(mail);
    }
    public void removeInboxMail(Inbox mail){
        inbox.remove(mail);
    }

    public List<Draft> getDraft() {
        return draft;
    }
    public void setDraft(List<Draft> draft) {
        this.draft = draft;
    }
    public void addDraftMail(Draft mail){
        if(draft == null)
            draft = new LinkedList<>();
        draft.add(mail);
    }
    public void removeDraftMail(Draft mail){
        draft.remove(mail);
    }

    public List<Trash> getTrash() {
        return trash;
    }
    public void setTrash(List<Trash> trash) {
        this.trash = trash;
    }
    public void addTrashMail(Trash mail){
        if(trash == null)
            trash = new LinkedList<>();
        trash.add(mail);
    }
    public void removeTrashMail(Trash mail){
        trash.remove(mail);
    }

}