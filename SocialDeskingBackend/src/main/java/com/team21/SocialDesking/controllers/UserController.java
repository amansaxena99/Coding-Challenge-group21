package com.team21.SocialDesking.controllers;

import com.team21.SocialDesking.entities.User;
import com.team21.SocialDesking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{email}")
    public User getUser(@PathVariable ("email") String email) {
        System.out.println(email);
        User user =userService.findUserByEmail(email);
        System.out.println(user);
        return user;
    }

}
