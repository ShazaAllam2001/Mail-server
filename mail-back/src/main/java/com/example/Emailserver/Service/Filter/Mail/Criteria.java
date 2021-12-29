package com.example.Emailserver.Service.Filter.Mail;

import com.example.Emailserver.UsersAndMails.Mail.Mail;

import java.util.List;

public interface Criteria {

    public List<? extends Mail> filterCriteria(List<? extends Mail> mailList);

}
