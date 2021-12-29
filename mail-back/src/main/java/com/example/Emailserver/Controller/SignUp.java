package com.example.Emailserver.Controller;

import com.example.Emailserver.Service.LoadAndSave.Save;
import com.example.Emailserver.UsersAndMails.User.IUser;
import org.json.JSONException;

public class SignUp {
    private Proxy proxy;

    public boolean addUser(IUser user) throws JSONException {
        Save save = new Save();
        save.saveUser(user);
        return true;
    }
}
