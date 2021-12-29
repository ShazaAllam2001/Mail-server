package com.example.Emailserver.Service.Filter.Mail;

import com.example.Emailserver.Service.Filter.Mail.Criteria;
import com.example.Emailserver.UsersAndMails.Mail.Mail;

import java.util.ArrayList;
import java.util.List;

public class ReceiversCriteria implements Criteria {
    private String R;
    public ReceiversCriteria(String receiver) {
        R = receiver;
    }

    @Override
    public List<? extends Mail> filterCriteria(List<? extends Mail> messages) {
        List<Mail> list = new ArrayList<>();
        for (Mail message : messages) {
            for(String r : message.getReceivers()) {
                if (r.equals(R)) {
                    list.add(message);
                }
            }
        }
        return list;
    }
}
