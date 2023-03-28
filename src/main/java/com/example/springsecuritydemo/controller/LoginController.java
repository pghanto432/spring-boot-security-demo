package com.example.springsecuritydemo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    Logger LOG = LogManager.getLogger(LoginController.class);
    @GetMapping ("/")
    public String login(){
        LOG.info("\n\n====> Inside login() method.........");
        return "Welcome Everybody...";
    }


    @GetMapping ("/user")
    public String user(){
        LOG.info("\n\n====> Inside user() method.........");
        return "Welcome User...";
    }

    @GetMapping ("/admin")
    public String admin(){
        LOG.info("\n\n====> Inside admin() method.........");
        return "Welcome Admin...";
    }
}
