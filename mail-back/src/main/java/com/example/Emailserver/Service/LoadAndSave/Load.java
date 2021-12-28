package com.example.Emailserver.Service.LoadAndSave;

import com.example.Emailserver.Service.Constants;
import com.example.Emailserver.UsersAndMails.Contact.IContact;
import com.example.Emailserver.UsersAndMails.Mail.Mail;
import com.example.Emailserver.UsersAndMails.User.IUser;
import com.example.Emailserver.UsersAndMails.User.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class Load {

    public IUser loadUserData(String userEmail) {
        JSONObject user = getUser(userEmail);
        // convert json object to java object
        IUser currentUser = new User((String) user.get("userName"),
                                     (String) user.get("password"),
                                     (String) user.get("email"));
        currentUser.setContacts((List<IContact>) user.get("contacts"));
        return currentUser;
    }

    private JSONObject getUser(String userEmail) {
        // load all users
        JSONArray usersList = LoadAsJSONArray.loadUsers();
        // search for a user with a specific email
        for(int i=0; i<usersList.size(); i++) {
            JSONObject obj;
            try {
                obj = (JSONObject) usersList.get(i);
                if(obj.get("email").equals(userEmail)) {
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
        JSONArray mailsList = LoadAsJSONArray.loadMails(filePath);
        // search for a user with a specific email
        for(int i=0; i<mailsList.size(); i++) {
            try {
                JSONObject obj = (JSONObject) mailsList.get(i);


            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
