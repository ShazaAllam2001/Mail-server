package com.example.Emailserver.Service.Sort;

import com.example.Emailserver.UsersAndMails.Mail.Mail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortMails {

    private List<Mail> mails;

    public SortMails(List<Mail> mails) {
        this.mails = mails;
    }

    public List<Mail> sortBySender() {
        List<Mail> mails = new ArrayList<>();
        Collections.copy(mails, this.mails);
        Collections.sort(mails, Comparator.comparing(Mail::getSender));
        return mails;
    }

    // sorts by the first email in the receivers list
    public List<Mail> sortByReceivers() {
        List<Mail> mails = new ArrayList<>();
        Collections.copy(mails , this.mails);
        Collections.sort(mails, Comparator.comparing(o -> o.getReceivers().get(0)));
        return mails;
    }


    public List<Mail> sortBySubject() {
        List<Mail> mails = new ArrayList<>();
        Collections.copy(mails, this.mails);
        Collections.sort(mails, Comparator.comparing(Mail::getSubject));
        return mails;
    }


    public List<Mail> sortByTime() {
        List<Mail> mails = new ArrayList<>();
        Collections.copy(mails, this.mails);
        Collections.sort(mails , Comparator.comparing(Mail::getTime));
        return mails ;
    }


    public List<Mail> sortByPriority() {
        List<Mail> mails = new ArrayList<>();
        Collections.copy(mails, this.mails);
        Collections.sort(mails, Comparator.comparing(Mail::getPriorityLevel));
        return mails;
    }

    public List<Mail> sortByBody() {
        List<Mail> mails = new ArrayList<>();
        Collections.copy(mails, this.mails);
        Collections.sort(mails, Comparator.comparing(Mail::getBody));
        return mails;
    }


    // sorts by the first attach in the attachments list by their name
    public List<Mail> sortByAttaches() {
        List<Mail> mails = new ArrayList<>();
        Collections.copy(mails , this.mails);
        Collections.sort(mails, Comparator.comparing(o -> o.getAttaches().get(0).getName()));
        return mails;
    }

}
