package com.example.Emailserver.UsersAndMails;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;

public class Attachments {
    private ArrayList<String> attaches;

    public Attachments(ArrayList<String> attaches) {
        super();
        this.attaches = attaches;
    }

    public ArrayList<String> getAttaches() {
        return attaches;
    }

}
