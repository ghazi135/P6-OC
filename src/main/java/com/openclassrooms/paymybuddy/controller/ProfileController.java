package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private TransactionService transactionService;


    @GetMapping(value = "/rechargeBankAccount")
    public void getBackMoneyOnMyBankAccount(@AuthenticationPrincipal User user, @RequestParam(name = "amount") Double amount) {

        transactionService.getBackMoneyOnMyBankAccount(user.getEmail(), amount);
    }

    @GetMapping(value = "/rechargePayMyBuddy")
    public void transferToMoneyApplication(@AuthenticationPrincipal User user, @RequestParam(value = "amount", required = false) Double amount) {

        transactionService.rechargeApplicationAccount(user.getEmail(), amount, user.getBankAccount().getIBAN());
    }


    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model) {


        return "profile";
    }


}
