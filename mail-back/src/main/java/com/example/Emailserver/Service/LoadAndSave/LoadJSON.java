package com.example.Emailserver.Service.LoadAndSave;

import com.example.Emailserver.Service.Constants;
import org.json.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class LoadJSON {

    public static JSONArray loadUsers() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader userReader = new FileReader(Constants.ACCOUNTS_JSON_PATH)) {
            //Read JSON file
            Object list = jsonParser.parse(userReader);
            org.json.simple.JSONArray tempList = (org.json.simple.JSONArray) list;
            JSONArray userList = new JSONArray(tempList.toJSONString());
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
            org.json.simple.JSONArray tempList = (org.json.simple.JSONArray) list;
            JSONArray mailList = new JSONArray(tempList.toJSONString());
            return mailList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
