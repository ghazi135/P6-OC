package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Registration {

    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/registration")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "registration";
    }


}
