package com.example.Emailserver.Controller;

import com.example.Emailserver.Service.Constants;
import com.example.Emailserver.Service.Server;
import com.example.Emailserver.UsersAndMails.Contact.Contact;
import com.example.Emailserver.UsersAndMails.Contact.IContact;
import com.example.Emailserver.UsersAndMails.Mail.Mail;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping
public class Controller {

    Server server = Server.getServer(); //Server to get and send data
    JSONParser jsonParser = new JSONParser();

    @GetMapping("/signUp/{username}/{email}/{password}")
    public boolean signUp(@PathVariable("username") String userName,
                          @PathVariable("email") String email,
                          @PathVariable("password") String password) throws JSONException {
        System.out.println("Sign up" + "\nUserName: " + userName + "\nEmail: " + email + "\nPassword: " + password);
        return server.signUp(userName, email, password);
    }

    @GetMapping("/signIn/{email}/{password}")
    public String signIn(@PathVariable("email") String email,
                         @PathVariable("password") String password) throws JSONException, IOException, ParseException, java.text.ParseException {
        System.out.println("Sign in" + "\nEmail: " + email + "\nPassword: " + password);
        return server.signIn(email, password);
    }

    @GetMapping("/signOut")
    public boolean signOut() {
        return server.signOut();
    }

    @PostMapping("/attachments")
    public boolean attachments(@RequestParam("file") MultipartFile[] files) {
        server.setAttachments(files);
        for(MultipartFile file : files) {
            try {
                file.getInputStream();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @PostMapping("/compose")
    public boolean compose(@RequestBody String messageString) throws IOException, java.text.ParseException, ParseException {
        org.json.simple.JSONObject tempObj = (org.json.simple.JSONObject) this.jsonParser.parse(messageString);
        JSONObject message = new JSONObject(tempObj.toJSONString());
        message.put("sender", server.getUserEmail());
        return server.createMessage(message);
    }

    @GetMapping("/contacts")
    public IContact[] contacts() {
        List<IContact> contacts = server.getContacts();
        return contacts.toArray(new Contact[contacts.size()]);
    }

    @GetMapping("/mails/{folder}")
    public Mail[] mails(@PathVariable("folder") String folder) throws IOException, ParseException {
        List<Mail> mails = server.getMails(folder);
        return mails.toArray(new Mail[mails.size()]);
    }

    @GetMapping("/mails/{folder}/{id}")
    public Mail getMail(@PathVariable("folder") String folder,@PathVariable("id") String id) {


        return null;
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
    public List<Mail> sort(@RequestParam(name ="inboxOrSent") String inboxOrSent){
        List<Mail> list = new ArrayList<>();
        List<? extends Mail> primary = server.Filter(Constants.TRUE ,Constants.PRIORITY , inboxOrSent);
        List<? extends Mail> defaultList = server.Filter(Constants.FALSE , Constants.PRIORITY , inboxOrSent);
        if(primary != null)
            list.addAll(primary);
        if(defaultList != null)
            list.addAll(defaultList);

        return list.isEmpty() ? null : list;
    }


    @GetMapping("/filter")
    public List<? extends Mail> filterBy(@RequestParam(name = "filterName")String Name ,
                                                 @RequestParam(name = "filterBy") String filterBY ,
                                                 @RequestParam(name = "place") String place){

        return server.Filter(Name, filterBY, place);
    }


    @GetMapping("/getUsername")
    public String getUsername() {
        return server.getUserName();
    }

    //@GetMapping("/addContact")

    //@GetMapping("/removeContact")


}