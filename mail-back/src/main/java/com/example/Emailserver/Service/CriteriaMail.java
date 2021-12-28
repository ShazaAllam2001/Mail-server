package com.example.Emailserver.Service;

import com.example.Emailserver.UsersAndMails.Mail.Mail;

import java.util.List;

public interface CriteriaMail {

    public List<Mail> meetCriteria(List<Mail> mailList);
}
