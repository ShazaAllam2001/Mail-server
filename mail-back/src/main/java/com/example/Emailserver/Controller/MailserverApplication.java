package com.example.Emailserver.Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MailserverApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MailserverApplication.class, args);

    }
}

