package com.example.Emailserver.Service.LoadAndSave;

import com.example.Emailserver.Service.Constants;
import org.json.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.File;
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
        org.json.simple.JSONArray tempList = new org.json.simple.JSONArray();
        String[] children = (new File(filePath)).list();
        for(String name : children) {
            if(name.contains(".json")) {
                try (FileReader mailReader = new FileReader(filePath + name)) {
                    //Read JSON file
                    Object obj = jsonParser.parse(mailReader);
                    org.json.simple.JSONObject tempObj = (org.json.simple.JSONObject) obj;
                    tempList.add(tempObj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return new JSONArray(tempList.toJSONString());
    }

}
