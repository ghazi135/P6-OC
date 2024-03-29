package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Friend;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.service.AccountService;
import com.openclassrooms.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/users")
public class UserController {


    @Autowired
    AccountService accountService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAllUsers() {

        return userService.findAll();
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> findById(@PathVariable Integer id) {

        return userService.findById(id);
    }

    @GetMapping(value = "/friends", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Friend> findAllFriends() {

        return userService.findAllFriends();
    }


    @DeleteMapping(value = "/friends")
    public void deleteFriend(@RequestParam(name = "idUser") Integer idUser, @RequestParam(name = "idUserFriend") Integer idUserFriend) {

        userService.deleteFriend(idUser, idUserFriend);
    }

    @GetMapping(value = "/friends/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Friend> findUserPrincipalFriendsByEmail(@PathVariable String email) {

        return userService.findFriendByPrincipalUserEmail(email);
    }


    @PutMapping(value = "/{id}")
    @ResponseBody
    public void updateUser(@PathVariable Integer id, @RequestBody User user) {

        userService.updateUser(id, user);
    }


}
