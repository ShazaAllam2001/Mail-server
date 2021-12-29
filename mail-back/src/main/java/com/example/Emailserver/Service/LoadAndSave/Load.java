package com.example.Emailserver.Service.LoadAndSave;

import com.example.Emailserver.Service.Constants;
import com.example.Emailserver.UsersAndMails.Contact.IContact;
import com.example.Emailserver.UsersAndMails.Mail.Attachment;
import com.example.Emailserver.UsersAndMails.Mail.Mail;
import com.example.Emailserver.UsersAndMails.Mail.MailCreation.MailBuilder;
import com.example.Emailserver.UsersAndMails.Mail.Priority;
import com.example.Emailserver.UsersAndMails.User.IUser;
import com.example.Emailserver.UsersAndMails.User.User;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class Load {
    private Gson gson;
    private Type contactType;
    private Type attachmentType;

    public Load() {
        this.gson = new Gson();
        this.contactType = new TypeToken<List<IContact>>(){}.getType();
        this.attachmentType = new TypeToken<List<Attachment>>(){}.getType();
    }

    public IUser loadUserData(String userEmail) throws JSONException {
        JSONObject user = getUser(userEmail);
        // convert json object to java object
        IUser currentUser = new User(user.getString("userName"),
                                     user.getString("password"),
                                     user.getString("email"));
        JSONArray contacts = user.getJSONArray("contacts");
        currentUser.setContacts(gson.fromJson(contacts.toString(),contactType));
        return currentUser;
    }

    private JSONObject getUser(String userEmail) {
        // load all users
        JSONArray usersList = LoadJSON.loadUsers();
        // search for a user with a specific email
        for(int i=0; i<usersList.length(); i++) {
            JSONObject obj;
            try {
                obj = (JSONObject) usersList.get(i);
                if(obj.getString("email").equals(userEmail)) {
                    return obj;
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Mail> loadUserMails(String userEmail, String folder) {
        String filePath = Constants.DATABASE_PATH + userEmail + "//" + folder + ".json";
        JSONArray mailsList = LoadJSON.loadMails(filePath);
        List<Mail> mails = new LinkedList<>();
        for(int i=0; i<mailsList.length(); i++) {
            try {
                JSONObject obj = (JSONObject) mailsList.get(i);
                // convert JSON object into java object
                MailBuilder mailBuilder = new MailBuilder(folder);
                mailBuilder.setSender(obj.getString("sender"))
                           .setReceivers(gson.fromJson(obj.getJSONArray("receivers").toString(),contactType))
                           .setSubject(obj.getString("subject"))
                           .setTime(obj.getString("time"))
                           .setPriority(Priority.values()[obj.getInt("priority")-1])
                           .setBody(obj.getString("body"))
                           .setAttaches(gson.fromJson(obj.getJSONArray("attachments").toString(),attachmentType));
                mails.add(mailBuilder.getMail());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
