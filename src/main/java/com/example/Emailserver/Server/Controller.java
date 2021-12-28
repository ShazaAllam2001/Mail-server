package com.example.Emailserver.Server;

import com.example.Emailserver.UsersAndMails.Contact;
import com.example.Emailserver.UsersAndMails.Message;
import com.example.Emailserver.UsersAndMails.MessageCreator;
import com.example.Emailserver.UsersAndMails.UserClass;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


@CrossOrigin
@RestController

public class Controller {

    Server server = Server.getServer();//Server to get and send data

    @GetMapping("/login")
    public boolean login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("Username is " + username + "\nPassword is " + password);
        try {
            return server.LOGIN(username, password);
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/signUp")
    @ResponseBody
    public boolean signUp(@RequestBody UserClass user) {
        System.out.println("Sign up");
        System.out.println(user);
        try {
            return server.signUp(user);}
        catch (Exception e) {
            return false;}
    }

    @GetMapping("/getUserUsername")
    public String getUsername() {
        return server.getUserName();
    }

    //send
    @PostMapping("/compose")
    public boolean compose(@RequestParam(name = "type") String type, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body
            , @RequestParam(name = "receivers") String receivers, @Nullable @RequestParam(name = "myFile") MultipartFile[] multipartFiles, @RequestParam("priority") boolean priority) {
        System.out.println("Title is " + title);
        System.out.println("Body is " + body);
        String[] receiverList = receivers.split(",");
        System.out.println(Arrays.toString(receiverList));
        ArrayList<String> recs = new ArrayList<>();
        Collections.addAll(recs, receiverList);
        try {
            return server.createMessage(title, body, Constants.handleFiles(multipartFiles), recs, type , priority);}
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

   // delete
    @PostMapping("/moveToTrash")
    @ResponseBody
    public void moveToTrash(@RequestBody int[] IDs, @RequestParam(name = "type") String type, @RequestParam(name = "toTrash") boolean toTrash) {
        System.out.println("IDs are " + Arrays.toString(IDs));

        try {
            for (int i : IDs) {
                if (toTrash) { server.sendToTrash(type, i);}
                else {server.restoreFromTrash(i);}
            }}
        catch (Exception ignored) {}
    }

    @GetMapping("/sort")
    public ArrayList<Message> sort(@RequestParam(name ="inboxOrSent") String inboxOrSent){
        ArrayList<Message> list = new ArrayList<>();
        ArrayList<? extends Message> primary = server.Filter(Constants.TRUE ,Constants.PRIORITY , inboxOrSent);
        ArrayList<? extends Message> defaultList = server.Filter(Constants.FALSE , Constants.PRIORITY , inboxOrSent);
        if(primary != null)
            list.addAll(primary);
        if(defaultList != null)
            list.addAll(defaultList);

        return list.isEmpty() ? null : list;
    }


    @GetMapping("/filter")
    public ArrayList<? extends Message> filterBy(@RequestParam(name = "filterName")String Name ,
                                                 @RequestParam(name = "filterBy") String filterBY ,
                                                 @RequestParam(name = "place") String place){

        return server.Filter(Name, filterBY, place);
    }


    
    //@GetMapping("/addContact")


}