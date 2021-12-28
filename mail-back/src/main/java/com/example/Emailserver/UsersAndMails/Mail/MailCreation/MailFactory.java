package com.example.Emailserver.UsersAndMails.Mail.MailCreation;

import com.example.Emailserver.Service.Constants;
import com.example.Emailserver.UsersAndMails.Mail.Mail;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Trash;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Draft;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Inbox;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Sent;

public class MailFactory {

	public Mail getMail (String name){
		if (name.equals(Constants.INBOX))
			return new Inbox();
		if (name.equals(Constants.SENT))
			return new Sent();
		if (name.equals(Constants.TRASH))
			return new Trash();
		if (name.equals(Constants.DRAFT))
			return new Draft();

		return null;
	}
}
