package com.example.Emailserver.Service.LoadAndSave;

import com.example.Emailserver.Service.Constants;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;

public class LoadSchema {

    public static Schema loadUserSchema() {
        // load userSchema
        try (FileReader userReader = new FileReader(Constants.SCHEMA_PATH + "userSchema.json")) {
            //Read JSON file
            JSONTokener jsonTokener = new JSONTokener(userReader.toString());
            JSONObject schemaDef = new JSONObject(jsonTokener);
            return SchemaLoader.load(schemaDef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Schema loadMailSchema() {
        // load mailSchema
        try (FileReader userReader = new FileReader(Constants.SCHEMA_PATH + "mailSchema.json")) {
            //Read JSON file
            JSONTokener jsonTokener = new JSONTokener(userReader.toString());
            JSONObject schemaDef = new JSONObject(jsonTokener);
            return SchemaLoader.load(schemaDef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
