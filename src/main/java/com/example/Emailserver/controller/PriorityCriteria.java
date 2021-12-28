package com.example.Emailserver.controller;

import com.example.Emailserver.UsersAndMails.Message;
import java.util.ArrayList;

public class PriorityCriteria implements Criteria {

    private boolean P;
    public PriorityCriteria(boolean priority) {
        this.P = priority;
    }

    @Override
    public ArrayList<? extends Message> filterCriteria(ArrayList<? extends Message> messages) {
        ArrayList<Message> list = new ArrayList<>();
        for (Message message : messages) {
            if (message.getHeader().isPriority() == P){
                list.add(message);
            }
        }
        return list;
    }
}
