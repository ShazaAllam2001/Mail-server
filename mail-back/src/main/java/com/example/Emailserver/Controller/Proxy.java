package com.example.Emailserver.Controller;

import com.example.Emailserver.Service.LoadAndSave.Load;
import com.example.Emailserver.UsersAndMails.User.IUser;
import org.json.JSONException;


public class Proxy {
    private String Email, password;
    private Load load;

    //set attribute of proxy class
    public Proxy(String Email,String password) {
        this.Email = Email;
        this.password = password;
        load = new Load();
    }

    public IUser ExistOrNot() throws JSONException {
        IUser existUser = load.loadUserData(Email);
        return existUser;
    }

}
