package com.example.Emailserver.UsersAndMails.Mail;

public class Attachment {
    private final String name;
    private final String path;

    public Attachment(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() { return name; }

    public String getPath() { return path; }

}
