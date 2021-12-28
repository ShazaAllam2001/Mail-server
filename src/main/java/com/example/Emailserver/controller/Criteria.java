package com.example.Emailserver.controller;

import com.example.Emailserver.UsersAndMails.Message;
import java.util.ArrayList;

public interface Criteria {

     ArrayList<? extends Message> filterCriteria (ArrayList<? extends Message> messages); }
