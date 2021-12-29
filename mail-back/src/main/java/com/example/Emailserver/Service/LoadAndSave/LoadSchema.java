package com.example.Emailserver.Service.LoadAndSave;

import com.example.Emailserver.Service.Constants;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class LoadSchema {

    public static Schema loadUserSchema() {
        // load userSchema
        JSONParser jsonParser = new JSONParser();
        try (FileReader userReader = new FileReader(Constants.SCHEMA_JSON_PATH + "userSchema.json")) {
            //Read JSON file
            JSONObject schemaDef = (JSONObject) jsonParser.parse(userReader);
            return SchemaLoader.load(schemaDef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Schema loadMailSchema() {
        // load mailSchema
        JSONParser jsonParser = new JSONParser();
        try (FileReader userReader = new FileReader(Constants.SCHEMA_JSON_PATH + "mailSchema.json")) {
            //Read JSON file
            JSONObject schemaDef = (JSONObject) jsonParser.parse(userReader);
            return SchemaLoader.load(schemaDef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
