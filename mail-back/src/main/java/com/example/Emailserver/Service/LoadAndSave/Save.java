package com.example.Emailserver.Service.LoadAndSave;

import com.example.Emailserver.Service.Constants;
import com.example.Emailserver.UsersAndMails.Mail.Mail;
import com.example.Emailserver.UsersAndMails.User.IUser;
import org.everit.json.schema.Schema;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;

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
            userWriter.close();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveEmail(Mail mail, String userEmail, String folder) throws JSONException, IOException {
        mail.setID();
        String filePath = Constants.DATABASE_PATH + userEmail + "\\" + folder + "\\" + mail.getID();
        File path = new File(filePath);
        path.mkdir();
        File emailFile = new File(filePath + "\\" + mail.getID() +".json" );
        emailFile.createNewFile();
        // convert Mail object to JSON object
        JSONObject JSONMail;
        JSONMail = convertToJSON.convertMail(mail);
        try(FileWriter mailWriter = new FileWriter(emailFile)) {
            // validate object to JSON schema
            mailSchema.validate(JSONMail);
            // write the new mail list
            mailWriter.write(JSONMail.toString());
            mailWriter.flush();
            mailWriter.close();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveAttachments(MultipartFile[] attachments, Mail mail, String userEmail, String folder) {
        for(MultipartFile file : attachments) {
            String path = Constants.DATABASE_PATH + userEmail + "\\" + folder + "\\" + mail.getID() + "\\attachments";
            new File(path).mkdir();
            path = path + "\\" + file.getOriginalFilename();
            try {
                File attach = new File(path);
                if(!attach.exists()) {
                    Files.copy(file.getInputStream(), Path.of(path), StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public void makeDirectory(String email) throws IOException {
        String dirPath = Constants.DATABASE_PATH + email;
        File dir = new File(dirPath);
        dir.mkdir();
        File sent = new File(dirPath + "\\sent");
        sent.mkdir();
        File inbox = new File(dirPath + "\\inbox");
        inbox.mkdir();
        File trash = new File(dirPath + "\\trash");
        trash.mkdir();
        File draft = new File(dirPath + "\\draft");
        draft.mkdir();
    }

    public void ClearFileContent(String mail,String type) throws IOException, ParseException {
        /*FileWriter fileWriter = new FileWriter(Constants.DATABASE_PATH + mail +"//"+type);
        fileWriter.write("");///clear the file
        fileWriter.flush();
        fileWriter.close();//close file*/
    }

}
