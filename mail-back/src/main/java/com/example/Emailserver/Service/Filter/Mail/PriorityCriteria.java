package com.example.Emailserver.Service.Filter.Mail;

import com.example.Emailserver.Service.Filter.Mail.Criteria;
import com.example.Emailserver.UsersAndMails.Mail.Mail;

import java.util.ArrayList;
import java.util.List;

public class PriorityCriteria implements Criteria {

    private int P;
    public PriorityCriteria(int priority) {
        this.P = priority;
    }

    @Override
    public List<? extends Mail> filterCriteria(List<? extends Mail> messages) {
        ArrayList<Mail> list = new ArrayList<>();
        for (Mail message : messages) {
            if(message.getPriorityLevel() == P){
                list.add(message);
            }
        }
        return list;
    }

}
