package com.example.Emailserver.UsersAndMails.Mail.MailCreation;

import com.example.Emailserver.Service.Constants;
import com.example.Emailserver.UsersAndMails.Mail.Mail;
import com.example.Emailserver.UsersAndMails.Mail.MailImmutable;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Trash;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Draft;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Inbox;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Sent;

public class MailFactory {

	public Mail getMail (String name){
		if (name.compareToIgnoreCase(Constants.INBOX)==0)
			return new Inbox();
		else if (name.compareToIgnoreCase(Constants.SENT)==0)
			return new Sent();
		else if (name.compareToIgnoreCase(Constants.TRASH)==0)
			return new Trash();
		else if (name.compareToIgnoreCase(Constants.DRAFT)==0)
			return new Draft();
		else
			return new MailImmutable();
	}
}
