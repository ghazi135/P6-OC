package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping(value="/user" , produces = MediaType.APPLICATION_JSON_VALUE)
    public  List<User> findAllUsers() {


        return userService.findAll();
    }

    @GetMapping(value="/user/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

}
