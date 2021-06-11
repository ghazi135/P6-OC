package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Friend;
import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.service.TransactionService;
import com.openclassrooms.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        List<Friend>      friendList      = userService.findFriendByPrincipalUserEmail(user.getEmail());
        model.addAttribute("balance", user.getMoneyAvailable());
        model.addAttribute("friends", friendList);
        model.addAttribute("userExptFriends", userService.usersExeptFriends(user.getEmail()));
        model.addAttribute("transactions", transactionList);
        model.addAttribute("user", new User());
        return "transfer";
    }

    @GetMapping(value = "/send")
    public String send(@AuthenticationPrincipal User user, @RequestParam(value = "emailFriend", required = false) String emailFriend, @RequestParam(value = "description", required = false) String description, @RequestParam(value = "amount", required = false) Double amount) {

        transactionService.sendMoney(user.getEmail(), amount, emailFriend, description);
        return "redirect:/dashboard";
    }


}
