package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.BankAccount;
import com.openclassrooms.paymybuddy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {


    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<BankAccount> findAll(){

        return accountService.findAllAccounts();
    }



    @GetMapping(value = "/{email}")
    public BankAccount findByEmail(@PathVariable String email){

        return accountService.findAccountByEmail(email);
    }
}
