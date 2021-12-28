package com.example.Emailserver.controller;

import java.io.*;
import java.util.ArrayList;

import com.example.Emailserver.UsersAndMails.Contact.Contact;
import com.example.Emailserver.UsersAndMails.MessageCreator;
import com.example.Emailserver.UsersAndMails.User.User;
import com.example.Emailserver.UsersAndMails.UserClass;
import com.example.Emailserver.Service.Constants;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SaveAndLoad {
	@SuppressWarnings("unchecked")
	public ArrayList<User> readUsersFromJson() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj ;
		try {
			obj = parser.parse(new FileReader(Constants.ACCOUNTS_JSON_PATH));
		}catch(Exception e) {
			return null;
		}
		ArrayList<User> usersArrayList = new ArrayList<>();
		ArrayList<User> usersReadOnlyArrayList=new ArrayList<User>();
		JSONArray employeeList = (JSONArray) obj;
		for(int i=0;i<employeeList.size();i++) {
			JSONObject objects = (JSONObject) employeeList.get(i);
			String user=(String) objects.get("user");
			Gson gson=new Gson();
			UserClass json= gson.fromJson(user, UserClass.class);
			User userReadOnly = new UserClass(json.getUserName(),json.getPassword(),json.getEmail());
			usersReadOnlyArrayList.add(userReadOnly);
			usersArrayList.add(json);
		}
		return usersReadOnlyArrayList;
	}

	public JSONArray readJsonArray() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(Constants.ACCOUNTS_JSON_PATH));
		}catch(Exception E){
			return new JSONArray();
		}

		JSONArray users = (JSONArray) obj;
		return users;
	}

	public void sendMessage(MessageCreator messageCreator, String type, String mail) throws IOException, ParseException {
		JSONArray  arrayOfSent;
		if(readJsonArrayOfPreviousSent(mail,type)!=null) {
			arrayOfSent=readJsonArrayOfPreviousSent(mail,type);
		}else {
			arrayOfSent=new JSONArray();
		}
		Gson gson=new Gson();
		String json= gson.toJson(messageCreator);
		JSONObject userJson2=new JSONObject();
		userJson2.put(Constants.MESSAGE,json);
		arrayOfSent.add(userJson2);

		FileWriter fileWriter=new FileWriter(Constants.DATABASE_PATH+mail+"//"+type+ Constants.INDEX_JSON_PATH);
		fileWriter.write(arrayOfSent.toJSONString());
		fileWriter.flush();
		fileWriter.close();////////close file 
	}
	public JSONArray readJsonArrayOfPreviousSent(String Email,String type) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(Constants.DATABASE_PATH + Email + "//" + type + Constants.INDEX_JSON_PATH));
			JSONArray prevoiusSent = (JSONArray) obj;
			return prevoiusSent;
		}catch (Exception ignored){
			return new JSONArray();
		}

	}
	@SuppressWarnings("unchecked")
	public ArrayList<MessageCreator> readMessages(String Email,String type) throws FileNotFoundException, IOException, ParseException {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(Constants.DATABASE_PATH + Email + "//" + type + Constants.INDEX_JSON_PATH));

			ArrayList<MessageCreator> MessagesArrayList = new ArrayList<MessageCreator>();

			JSONArray employeeList = (JSONArray) obj;
			for (int i = 0; i < employeeList.size(); i++) {
				JSONObject objects = (JSONObject) employeeList.get(i);

				String user = (String) objects.get(Constants.MESSAGE);
				Gson gson = new Gson();
				MessageCreator json = gson.fromJson(user, MessageCreator.class);
				MessagesArrayList.add(json);
			}
			return MessagesArrayList;
		}catch (Exception e){
			return null;
		}
	}

//read contact from json
	@SuppressWarnings("unchecked")
	public ArrayList<Contact> readContactsFromJson(String Email) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj ;
		try {
			obj = parser.parse(new FileReader(Constants.DATABASE_PATH+Email+"//"+Constants.CONTACTS+ Constants.INDEX_JSON_PATH));}
		catch(Exception e) {return null;}
		ArrayList<Contact> usersArrayList=new ArrayList<Contact>();

		JSONArray Contacts = (JSONArray) obj;
		for(int i=0;i<Contacts.size();i++) {
			JSONObject objects = (JSONObject) Contacts.get(i);

			String Contact=(String) objects.get(Constants.CONTACTS);
			Gson gson=new Gson();
			Contact json= gson.fromJson(Contact,Contact.class);

			usersArrayList.add(json);
		}
		return usersArrayList;
	}

	public int getMessageID(String Email,String folder) throws FileNotFoundException, IOException, ParseException {
		ArrayList<MessageCreator> folderMessage = readMessages(Email,folder);
		if(folderMessage==null) {
			return 1;
		}else {
			MessageCreator lastMessage=folderMessage.get(folderMessage.size()-1);
			return (lastMessage.getID())+1;
		}
	}

	public void makeFirstDirectory() throws IOException {
		File file9 = new File(Constants.DATABASE_PATH);
		file9.mkdir();
		File file10 = new File(Constants.ACCOUNTS_PATH  );
		file10.mkdir();//make accounts folder

		File indexFileDraft = new File(Constants.ACCOUNTS_JSON_PATH );
		indexFileDraft.createNewFile();
	}

	public void ClearFileContent(String mail,String type) throws IOException, ParseException {
		JSONArray  arrayOfSent;
		arrayOfSent=new JSONArray();
		FileWriter fileWriter=new FileWriter(Constants.DATABASE_PATH +mail+"//"+type+ Constants.INDEX_JSON_PATH);
		fileWriter.write("");///clear the file
		fileWriter.flush();
		fileWriter.close();//close file
	}

}