package com.example.Emailserver.Service.Filter.Mail;

import com.example.Emailserver.Service.Filter.Mail.Criteria;
import com.example.Emailserver.UsersAndMails.Mail.Mail;

import java.util.ArrayList;
import java.util.List;

public class SubjectCriteria implements Criteria {

    private String S;
    public SubjectCriteria(String subject) {S = subject;}

    @Override
    public List<? extends Mail> filterCriteria(List<? extends Mail> messages) {
        List<Mail> list = new ArrayList<>();
        for (Mail message : messages) {
            if (message.getSubject().contains(S)){
                list.add(message);
            }
        }
        return list;
    }

}
