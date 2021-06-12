package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> findAll() {

        return transactionService.findAllTransactions();
    }



    @PostMapping(value = "/rechargeBankAccount")
    public void getBackMoneyOnMyBankAccount(@RequestParam(name = "email") String email, @RequestParam(name = "amount") Double amount) {

        transactionService.getBackMoneyOnMyBankAccount(email, amount);
    }

    @PostMapping(value = "/rechargePayMyBuddy")
    public void rechargeApplicationAccount(@RequestParam(name = "email") String email, @RequestParam(name = "amount") Double amount, @RequestParam(name = "iban") String iban) {

        transactionService.rechargeApplicationAccount(email, amount, iban);
    }
}
