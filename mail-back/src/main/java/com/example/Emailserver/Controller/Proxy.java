package com.example.Emailserver.Controller;

import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.example.Emailserver.UsersAndMails.User.User;
import com.example.Emailserver.UsersAndMails.UserClass;

public class Proxy {
    private String Email, password;
    private UserClass userClass;
    private SaveAndLoad saveAndLoad;

    //set attribute of proxy class
    public Proxy(String Email,String password) {
        this.Email=Email;
        this.password=password;
        saveAndLoad=new SaveAndLoad();
    }

    public User logIn() throws FileNotFoundException, IOException, ParseException {
        ArrayList<User> ExistUser;
        ExistUser=saveAndLoad.readUsersFromJson();
        for(int i=0;i<ExistUser.size();i++){
            if(Email.equals((String) ExistUser.get(i).getEmail())&&password.equals((String) ExistUser.get(i).getPassword())){
                return ExistUser.get(i);
            }
        }
        return null;

    }


}
