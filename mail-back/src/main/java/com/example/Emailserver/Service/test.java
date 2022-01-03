package com.example.Emailserver.Service;

import java.io.File;
import java.io.IOException;

import static com.example.Emailserver.Service.LoadAndSave.Save.intialFiles;

public class test {

    public static void main(String[] args) throws IOException {
        String email = "shaza@hotmail.com";
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
}
