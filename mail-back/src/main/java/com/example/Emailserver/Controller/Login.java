package com.example.Emailserver.Controller;

import com.example.Emailserver.UsersAndMails.User.IUser;
import org.json.JSONException;

public class Login {
	private String password, Email;
	private Proxy proxy;

	public Login(String Email,String password) {
		this.Email = Email;
		this.password = password;
	}

	public IUser ExistOrNot() throws JSONException {
		proxy = new Proxy(Email,password);
		return proxy.logIn();
	}
}
