package com.example.Emailserver.controller;

import com.example.Emailserver.UsersAndMails.Message;
import java.util.ArrayList;

public class receiversCriteria  implements Criteria{

    private String R;
    public receiversCriteria(String receiver) {
        R =receiver;
    }

    @Override
    public ArrayList<? extends Message> filterCriteria(ArrayList<? extends Message> messages) {
        ArrayList<Message> list = new ArrayList<>();
        for (Message message : messages) {
            for(String r : message.getHeader().getReceiver())
                if (r.equals(R)){
                    list.add(message);
                }
        }
        return list;
    }
}
