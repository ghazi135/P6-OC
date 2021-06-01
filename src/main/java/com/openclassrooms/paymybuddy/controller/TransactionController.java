package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    private void sendMoney(@RequestParam(name = "emailSender") String emailSender, @RequestParam(name = "amount") Double amount,@RequestParam(name = "emailReceiver") String emailReceiver, @RequestParam(name = "description") String description){
        transactionService.sendMoney(emailSender, amount, emailReceiver, description);
    }
}
