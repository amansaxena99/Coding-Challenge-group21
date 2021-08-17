package com.team21.SocialDesking.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin
public class TestController {

    @GetMapping("/test")
    public String test() {
        System.out.println("Hello");
        return "Connection Successful";
    }
}
