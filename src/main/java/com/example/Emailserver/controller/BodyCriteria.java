package com.example.Emailserver.controller;

import com.example.Emailserver.UsersAndMails.Message;
import java.util.ArrayList;

public class BodyCriteria implements Criteria {

    private String B;
    public BodyCriteria(String body) {
        B = body;
    }

    @Override
    public ArrayList<? extends Message> filterCriteria(ArrayList<? extends Message> messages) {
        ArrayList<Message> list = new ArrayList<>();
        for (Message message : messages) {
            if (message.getBody().getBody().contains(B)){
                list.add(message);
            }
        }
        return list;
    }
}