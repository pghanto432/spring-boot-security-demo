package com.example.springsecuritydemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {

    @GetMapping ("/")
    public String login(){
        log.info("\n\n====> Inside login() method.........");
        return "Welcome Everybody...";
    }


    @GetMapping ("/user")
    public String user(){
        log.info("\n\n====> Inside user() method.........");
        return "Welcome User...";
    }

    @GetMapping ("/admin")
    public String admin(){
        log.info("\n\n====> Inside admin() method.........");
        return "Welcome Admin...";
    }
}
