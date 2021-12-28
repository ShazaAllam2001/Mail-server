package com.example.Emailserver.controller;

import com.example.Emailserver.Service.Constants;
import com.example.Emailserver.Server.Server;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


@CrossOrigin
@RestController
@RequestMapping
public class Controller {

    Server server = Server.getServer(); //Server to get and send data

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


    //@GetMapping("/addContact")


}