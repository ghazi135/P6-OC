package com.openclassrooms.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Transfer {

    @GetMapping(value = "/dashboard")
    public String transfer() {
        return "transfer";
    }
}
