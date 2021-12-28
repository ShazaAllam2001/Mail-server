package com.example.Emailserver.controller;

import com.example.Emailserver.UsersAndMails.Message;
import java.util.ArrayList;

public class SubjectCriteria implements Criteria {

    private String S;
    public SubjectCriteria(String subject) {S = subject;}

    @Override
    public ArrayList<? extends Message> filterCriteria(ArrayList<? extends Message> messages) {
        ArrayList<Message> list = new ArrayList<>();
        for (Message message : messages) {
            if (message.getHeader().getSubject().contains(S)){
                list.add(message);
            }
        }
        return list;
    }
}
