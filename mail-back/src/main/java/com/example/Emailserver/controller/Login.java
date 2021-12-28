package com.example.Emailserver.controller;

import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.example.Emailserver.UsersAndMails.User.User;

public class Login {
	private String password, Email;
	private Proxy proxy;

	public Login(String Email,String password) {
		this.Email=Email;
		this.password=password;
	}

	public User ExistOrNot() throws FileNotFoundException, IOException, ParseException {
		proxy=new Proxy(Email,password);
		return proxy.logIn();
	}
}
