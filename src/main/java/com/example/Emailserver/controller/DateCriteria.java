package com.example.Emailserver.controller;

import com.example.Emailserver.UsersAndMails.Message;
import java.util.ArrayList;

public class DateCriteria implements Criteria {

    private String D;
    public DateCriteria(String date) {
        D=date;
    }

    @Override
    public ArrayList<? extends Message> filterCriteria(ArrayList<? extends Message> messages) {
        ArrayList<Message> list = new ArrayList<>();
        for (Message message : messages) {
            if (message.getTime().equals(D)){
                list.add(message);
            }
        }
        return list;
    }
}
