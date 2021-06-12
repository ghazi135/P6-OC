package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class Registration {

    @Autowired
    private UserService userService;


    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/registration")
    public String addUser(Model model) {

        User user = new User();
        model.addAttribute(user);
        return "registration";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(value = "/save")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {

        userService.saveUser(user);
        return "login";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {

        return "login";
    }

}
