package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.BankAccount;
import com.openclassrooms.paymybuddy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {


    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<BankAccount> findAll() {

        return accountService.findAllAccounts();
    }


    @GetMapping(value = "/{email}")
    public BankAccount findByEmail(@PathVariable String email) {

        return accountService.findAccountByEmail(email);
    }

    @PostMapping(value = "/{id}")
    public void saveBankAccount(@PathVariable Integer id, @RequestBody BankAccount bankAccount) {

        accountService.saveBankAccount(id, bankAccount);
    }

    @DeleteMapping(value = "{id}")
    public void deleteBankAccount(@PathVariable Integer id) {

        accountService.deleteAccount(id);

    }

}
