package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Friend;
import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.TransactionService;
import com.openclassrooms.paymybuddy.service.UserService;
import org.apache.maven.artifact.repository.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.validation.Valid;
import java.util.List;


@Controller
public class Transfer {


    @Autowired
    UserService        userService;
    @Autowired
    TransactionService transactionService;

    @GetMapping(value = "/dashboard")
    public String transfer(@AuthenticationPrincipal User user, Model model) {
        List<Transaction> transactionList = transactionService.findTransactionsOfPrincipalUser(user);
        List<Friend> friendList = userService.findFriendByPrincipalUserEmail(user.getEmail());
        model.addAttribute("balance", user.getMoneyAvailable());
        model.addAttribute("friends", friendList);
        model.addAttribute("transactions", transactionList);
        model.addAttribute("user", new User());
        return "transfer";
    }



}
