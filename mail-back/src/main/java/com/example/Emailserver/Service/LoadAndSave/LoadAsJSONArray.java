package com.example.Emailserver.Service.LoadAndSave;

import com.example.Emailserver.Service.Constants;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class LoadAsJSONArray {

    public static JSONArray loadUsers() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader userReader = new FileReader(Constants.DATABASE_PATH + "users.json")) {
            //Read JSON file
            Object list = jsonParser.parse(userReader);
            JSONArray userList = (JSONArray) list;
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray loadMails(String filePath) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader mailReader = new FileReader(filePath)) {
            //Read JSON file
            Object list = jsonParser.parse(mailReader);
            JSONArray mailList = (JSONArray) list;
            return mailList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
