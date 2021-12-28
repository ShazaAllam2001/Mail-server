package com.example.Emailserver.UsersAndMails.User;

import com.example.Emailserver.UsersAndMails.Contact.IContact;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Draft;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Inbox;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Sent;
import com.example.Emailserver.UsersAndMails.Mail.MailTypes.Trash;

import java.util.List;

public interface IUser {

	public String getUserName();
	public void setUserName(String userName);

	public String getPassword();
	public void setPassword(String password);

	public String getEmail();

	public List<IContact> getContacts();
	public void setContacts(List<IContact> contacts);
	public void addContact(IContact contact);
	public void removeContact(IContact contact);

	public List<Sent> getSent();
	public void setSent(List<Sent> sent);
	public void addSentMail(Sent mail);
	public void removeSentMail(Sent mail);

	public List<Inbox> getInbox();
	public void setInbox(List<Inbox> inbox);
	public void addInboxMail(Inbox mail);
	public void removeInboxMail(Inbox mail);

	public List<Draft> getDraft();
	public void setDraft(List<Draft> draft);
	public void addDraftMail(Draft mail);
	public void removeDraftMail(Draft mail);

	public List<Trash> getTrash();
	public void setTrash(List<Trash> trash);
	public void addTrashMail(Trash mail);
	public void removeTrashMail(Trash mail);

}
