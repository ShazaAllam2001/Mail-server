package com.example.Emailserver.Service.LoadAndSave;

import com.example.Emailserver.Service.Constants;
import com.example.Emailserver.UsersAndMails.Mail.Mail;
import com.example.Emailserver.UsersAndMails.User.IUser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.xml.validation.Schema;
import java.io.FileWriter;

public class Save {

    public boolean saveUser(IUser user) {
        // convert User object to JSON object
        JSONObject JSONUser;
        JSONUser = convertToJSON.convertUser(user);
        // read users file as JSON Array
        JSONArray userList = LoadAsJSONArray.loadUsers();
        // add the new user to the list
        userList.add(JSONUser);
        try(FileWriter userWriter = new FileWriter(Constants.DATABASE_PATH + "users.json")) {
            Schema
            userWriter.write(userList.toJSONString());
            userWriter.flush();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveEmail(Mail mail, String userEmail, String folder) {
        String filePath = Constants.DATABASE_PATH + userEmail + "//" + folder + ".json";
        // convert Mail object to JSON object
        JSONObject JSONMail;
        JSONMail = convertToJSON.convertMail(mail);
        // read mails file as JSON Array
        JSONArray mailList = LoadAsJSONArray.loadMails(filePath);
        // add the new mail to the list
        mailList.add(JSONMail);
        try(FileWriter userWriter = new FileWriter(filePath)) {
            userWriter.write(JSONMail.toJSONString());
            userWriter.flush();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Schema readSchema() {
        return null;
    }
}
