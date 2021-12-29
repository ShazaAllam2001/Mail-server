package com.example.Emailserver.UsersAndMails.User;

import com.example.Emailserver.UsersAndMails.Contact.IContact;
import com.example.Emailserver.UsersAndMails.Mail.Mail;

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

	public List<Mail> getSent();
	public void setSent(List<Mail> sent);
	public void addSentMail(Mail mail);
	public void removeSentMail(Mail mail);

	public List<Mail> getInbox();
	public void setInbox(List<Mail> inbox);
	public void addInboxMail(Mail mail);
	public void removeInboxMail(Mail mail);

	public List<Mail> getDraft();
	public void setDraft(List<Mail> draft);
	public void addDraftMail(Mail mail);
	public void removeDraftMail(Mail mail);

	public List<Mail> getTrash();
	public void setTrash(List<Mail> trash);
	public void addTrashMail(Mail mail);
	public void removeTrashMail(Mail mail);

	public void addFolder(String name);
	public void removeFolder(String name);
	public void renameFolder(String name, String newName);
	public void addToFolder(String name, Mail mail);
	public void removeFromFolder(String name, Mail mail);
	public List<Mail> getFolderMails(String name);

}
