package com.example.Emailserver.Service.LoadAndSave;

import com.example.Emailserver.Service.Constants;
import com.example.Emailserver.UsersAndMails.Mail.Mail;
import com.example.Emailserver.UsersAndMails.User.IUser;
import org.everit.json.schema.Schema;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        try(FileWriter userWriter = new FileWriter(Constants.ACCOUNTS_JSON_PATH)) {
            // validate object to JSON schema
            userSchema.validate(JSONUser);
            // make user directory
            makeDirectory(user.getEmail());
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

    public static void makeDirectory(String email) throws IOException {
        String dirPath = Constants.DATABASE_PATH + email;
        File dir = new File(dirPath);
        dir.mkdir();
        File sent = new File(dirPath + "\\sent.json");
        sent.createNewFile();
        intialFiles(sent);
        File inbox = new File(dirPath + "\\inbox.json");
        inbox.createNewFile();
        intialFiles(inbox);
        File trash = new File(dirPath + "\\trash.json");
        trash.createNewFile();
        intialFiles(trash);
        File draft = new File(dirPath + "\\draft.json");
        draft.createNewFile();
        intialFiles(draft);
    }

    public static void intialFiles(File file) {
        JSONArray JSONMail = new JSONArray();
        try(FileWriter userWriter = new FileWriter(file)) {
            // write an empty mail list
            userWriter.write(JSONMail.toString());
            userWriter.flush();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
