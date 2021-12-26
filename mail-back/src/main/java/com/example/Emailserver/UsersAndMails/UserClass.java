package com.example.Emailserver.UsersAndMails;
import java.util.ArrayList;

public class UserClass implements User {
    private String userName;
    private String password;
    private String Email;
    private ArrayList<Contact> Contacts;
    private ArrayList<Sent> sentMessage;
    private ArrayList<Inbox> inbox;
    private ArrayList<Draft> draft;
    private ArrayList<Trash> trash;

    public UserClass(String userName, String password, String email) {
        super();
        this.userName = userName;
        this.password = password;
      Email = email;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public ArrayList<Contact> getContacts() {
        return Contacts;
    }
    public void setContacts(ArrayList<Contact> contacts) {
        Contacts = contacts;
    }
    public ArrayList<Sent> getSentMessage() {
        return sentMessage;
    }
    public void setSentMessage(ArrayList<Sent> sentMessage) { this.sentMessage = sentMessage; }
    public ArrayList<Inbox> getInbox() {
        return inbox;
    }
    public void setInbox(ArrayList<Inbox> inbox) {
        this.inbox = inbox;
    }
    public ArrayList<Draft> getDraft() {
        return draft;
    }
    public void setDraft(ArrayList<Draft> draft) {
        this.draft = draft;
    }
    public ArrayList<Trash> getTrash() {
        return trash;
    }
    public void setTrash(ArrayList<Trash> trash) {
        this.trash = trash;
    }
    public String getEmail() {
        return Email;
    }


    public void addsentMessage(Sent message){
        if(sentMessage == null)
            sentMessage = new ArrayList<>();
        sentMessage.add(message);
    }
    public void addInboxMessage(Inbox message){
        if(inbox == null)
            inbox = new ArrayList<>();
        inbox.add(message);
    }
    public void addDraftMessage(Draft message){
        if(draft == null)
            draft = new ArrayList<>();
        draft.add(message);
    }
    public void addTrashMessage(Trash message){
        if(trash == null)
            trash = new ArrayList<>();
        trash.add(message);
    }

    public void addContact(Contact myContact){
        if(Contacts == null)
            Contacts = new ArrayList<>();
        Contacts.add(myContact);
    }

}