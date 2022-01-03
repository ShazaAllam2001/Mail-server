package com.example.Emailserver.Service.LoadAndSave;

import com.example.Emailserver.Service.Constants;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.FileReader;

public class LoadJSON {

    public static JSONArray loadUsers() {
        try (FileReader userReader = new FileReader(Constants.ACCOUNTS_JSON_PATH)) {
            //Read JSON file
            JSONTokener jsonTokener = new JSONTokener(userReader.toString());
            JSONArray userList = new JSONArray(jsonTokener);
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray loadMails(String filePath) {
        try (FileReader mailReader = new FileReader(filePath)) {
            //Read JSON file
            JSONTokener jsonTokener = new JSONTokener(mailReader.toString());
            JSONArray mailList = new JSONArray(jsonTokener);
            return mailList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
