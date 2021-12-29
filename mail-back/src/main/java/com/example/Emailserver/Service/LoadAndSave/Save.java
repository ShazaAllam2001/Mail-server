package com.example.Emailserver.Service.LoadAndSave;

import com.example.Emailserver.Service.Constants;
import com.example.Emailserver.UsersAndMails.Mail.Mail;
import com.example.Emailserver.UsersAndMails.User.IUser;
import org.everit.json.schema.Schema;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;

public class Save {
    Schema userSchema;
    Schema mailSchema;

    public Save() {
        this.userSchema = LoadSchema.loadUserSchema();
        this.mailSchema = LoadSchema.loadMailSchema();
    }

    public boolean saveUser(IUser user) throws JSONException {
        // convert User object to JSON object
        JSONObject JSONUser;
        JSONUser = convertToJSON.convertUser(user);
        // read users file as JSON Array
        JSONArray userList = LoadJSON.loadUsers();
        try(FileWriter userWriter = new FileWriter(Constants.DATABASE_PATH + "users.json")) {
            // validate object to JSON schema
            userSchema.validate(JSONUser);
            // add the new user to the list
            userList.put(JSONUser);
            // write the new user list
            userWriter.write(userList.toString());
            userWriter.flush();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveEmail(Mail mail, String userEmail, String folder) throws JSONException {
        String filePath = Constants.DATABASE_PATH + userEmail + "//" + folder + ".json";
        // convert Mail object to JSON object
        JSONObject JSONMail;
        JSONMail = convertToJSON.convertMail(mail);
        // read mails file as JSON Array
        JSONArray mailList = LoadJSON.loadMails(filePath);
        try(FileWriter userWriter = new FileWriter(filePath)) {
            // validate object to JSON schema
            mailSchema.validate(JSONMail);
            // add the new mail to the list
            mailList.put(JSONMail);
            // write the new mail list
            userWriter.write(JSONMail.toString());
            userWriter.flush();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
