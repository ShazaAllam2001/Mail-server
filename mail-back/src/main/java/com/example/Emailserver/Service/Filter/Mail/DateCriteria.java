package com.example.Emailserver.Service.Filter.Mail;

import com.example.Emailserver.Service.Filter.Mail.Criteria;
import com.example.Emailserver.UsersAndMails.Mail.Mail;

import java.util.ArrayList;
import java.util.List;

public class DateCriteria implements Criteria {

    private String D;
    public DateCriteria(String date) {
        D = date;
    }

    @Override
    public List<? extends Mail> filterCriteria(List<? extends Mail> messages) {
        List<Mail> list = new ArrayList<>();
        for (Mail message : messages) {
            if (message.getTime().equals(D)){
                list.add(message);
            }
        }
        return list;
    }

}
