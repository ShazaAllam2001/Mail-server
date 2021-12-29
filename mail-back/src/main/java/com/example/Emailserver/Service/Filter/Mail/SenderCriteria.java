package com.example.Emailserver.Service.Filter.Mail;

import com.example.Emailserver.Service.Filter.Mail.Criteria;
import com.example.Emailserver.UsersAndMails.Mail.Mail;

import java.util.ArrayList;
import java.util.List;

public class SenderCriteria implements Criteria {

    private String R;
    public SenderCriteria(String sender) {
        R = sender;
    }

    @Override
    public List<? extends Mail> filterCriteria(List<? extends Mail> messages) {
        List<Mail> list = new ArrayList<>();
        for (Mail message : messages) {
            if (message.getSender().equals(R)){
                list.add(message);
            }
        }
        return list;
    }

}
