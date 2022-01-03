package com.example.Emailserver.Service.LoadAndSave;

import com.example.Emailserver.Service.Constants;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class LoadSchema {

    public static Schema loadUserSchema() {
        JSONParser jsonParser = new JSONParser();
        // load userSchema
        try (FileReader userReader = new FileReader(Constants.USER_SCHEMA_PATH)) {
            //Read JSON file
            Object list = jsonParser.parse(userReader);
            org.json.simple.JSONObject tempObj = (org.json.simple.JSONObject) list;
            JSONObject schemaDef = new JSONObject(tempObj.toJSONString());
            return SchemaLoader.load(schemaDef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Schema loadMailSchema() {
        JSONParser jsonParser = new JSONParser();
        // load mailSchema
        try (FileReader userReader = new FileReader(Constants.MAIL_SCHEMA_PATH)) {
            ///Read JSON file
            Object list = jsonParser.parse(userReader);
            org.json.simple.JSONObject tempObj = (org.json.simple.JSONObject) list;
            JSONObject schemaDef = new JSONObject(tempObj.toJSONString());
            return SchemaLoader.load(schemaDef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
