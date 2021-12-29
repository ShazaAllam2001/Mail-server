package com.example.Emailserver.Service.Filter.Mail;

import com.example.Emailserver.UsersAndMails.Mail.Mail;

import java.util.ArrayList;
import java.util.List;

public class BodyCriteria implements Criteria {

    private String B;
    public BodyCriteria(String body) {
        B = body;
    }

    @Override
    public List<? extends Mail> filterCriteria(List<? extends Mail> messages) {
        List<Mail> list = new ArrayList<>();
        for (Mail message : messages) {
            if (message.getBody().contains(B)) {
                list.add(message);
            }
        }
        return list;
    }
}
