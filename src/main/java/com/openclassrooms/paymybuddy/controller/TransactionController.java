package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.entity.User;
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


    @GetMapping(value = "/byPrincipalUser")
    public List<Transaction> findTransactionsOfPrincipalUser(@RequestBody User user) {

        return transactionService.findTransactionsOfPrincipalUser(user);
    }

    @PostMapping
    public void sendMoney(@RequestParam(name = "emailSender") String emailSender, @RequestParam(name = "amount") Double amount, @RequestParam(name = "emailReceiver") String emailReceiver, @RequestParam(name = "description") String description) {

        transactionService.sendMoney(emailSender, amount, emailReceiver, description);
    }

    @PostMapping(value = "/rechargeBankAccount")
    public void getBackMoneyOnMyBankAccount(@RequestParam(name = "email") String email, @RequestParam(name = "amount") Double amount){
        transactionService.getBackMoneyOnMyBankAccount(email,amount);
    }

    @PostMapping(value = "/rechargePayMyBuddy")
    public void rechargeApplicationAccount(@RequestParam(name = "email") String email, @RequestParam(name = "amount") Double amount, @RequestParam(name = "iban") String iban){
        transactionService.rechargeApplicationAccount(email,amount,iban);
    }
}
