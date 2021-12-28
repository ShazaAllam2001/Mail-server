package com.example.Emailserver.Service.LoadAndSave;

import com.example.Emailserver.Service.Constants;
import com.example.Emailserver.UsersAndMails.Contact.IContact;
import com.example.Emailserver.UsersAndMails.Mail.Attachment;
import com.example.Emailserver.UsersAndMails.Mail.Mail;
import com.example.Emailserver.UsersAndMails.User.IUser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class convertToJSON {

    public static JSONObject convertUser(IUser user) {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("userName",user.getUserName());
        jsonUser.put("password",user.getPassword());
        jsonUser.put("email",user.getEmail());
        JSONArray contactsList = new JSONArray();
        for(IContact contact : user.getContacts()) {
            contactsList.add(convertContact(contact));
        }
        jsonUser.put("path", Constants.DATABASE_PATH + user.getEmail());
        return jsonUser;
    }

    public static JSONObject convertContact(IContact contact) {
        JSONObject jsonContact = new JSONObject();
        jsonContact.put("name",contact.getName());
        /* get emails list */
        JSONArray emailsList = new JSONArray();
        for(String email : contact.getEmails()) {
            emailsList.add(email);
        }
        jsonContact.put("mails",emailsList);
        return jsonContact;
    }

    public static JSONObject convertMail(Mail mail) {
        JSONObject jsonMail= new JSONObject();
        jsonMail.put("id",mail.getID());
        jsonMail.put("path",mail.getPath());
        jsonMail.put("sender",mail.getSender());
        /* get receivers list */
        JSONArray jsonReceivers = convertReceivers(mail.getReceivers());
        jsonMail.put("receivers",jsonReceivers);
        jsonMail.put("subject",mail.getSubject());
        jsonMail.put("time",mail.getTime());
        jsonMail.put("priority",mail.getPriorityLevel());
        jsonMail.put("body",mail.getBody());
        /* get attachments list */
        JSONArray jsonAttachments = convertAttachments(mail.getAttaches());
        jsonMail.put("attachments",jsonAttachments);
        jsonMail.put("mutable",mail.isMutable());
        return jsonMail;
    }

    public static JSONArray convertReceivers(List<String> receivers) {
        JSONArray receiversList = new JSONArray();
        for(String receiver : receivers) {
            receiversList.add(receiver);
        }
        return receiversList;
    }

    public static JSONArray convertAttachments(List<Attachment> attachments) {
        JSONArray attachmentsList = new JSONArray();
        for(Attachment attachment : attachments) {
            JSONObject jsonAttachment= new JSONObject();
            jsonAttachment.put("name",attachment.getName());
            jsonAttachment.put("path",attachment.getPath());
            attachmentsList.add(jsonAttachment);
        }
        return attachmentsList;
    }


}
