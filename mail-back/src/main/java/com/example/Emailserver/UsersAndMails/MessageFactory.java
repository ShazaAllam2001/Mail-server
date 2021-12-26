package com.example.Emailserver.UsersAndMails;

import com.example.Emailserver.Server.Constants;

public class MessageFactory {

	public Message getMessage (String name ,MessageHeader header, MessageBody body,Attachments attaches, String time,int ID,boolean priority){
		if (name.equals(Constants.INBOX))
			return new Inbox(ID,body,header,attaches,time,priority);
		if (name.equals(Constants.SENT))
			return new Sent(ID,body,header,attaches,time,priority);
		if (name.equals(Constants.TRASH))
			return new Trash(ID,body,header,attaches,time,priority);
		if (name.equals(Constants.DRAFT))
			return new Draft(ID,body,header,attaches,time,priority);

		return null;
	}
}
