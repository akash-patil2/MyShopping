package com.MyShopping.MyShopping.controller;


import com.MyShopping.MyShopping.models.AppUser;
import com.MyShopping.MyShopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // if any request starting with an /api that means all those requests are going to come at common controller

public class CommonController {

    @Autowired
    UserService userService;
    @PostMapping("/user/register")

    public String registerUser(@RequestBody AppUser appUser){

        userService.registerUser(appUser);
        return "User saved successfully";
    }
}
