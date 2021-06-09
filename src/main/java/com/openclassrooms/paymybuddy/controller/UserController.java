package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Friend;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;



@Controller
@RequestMapping(value = "/users")
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping(value = "/index")
    public String index() {


        return "transfer";
    }

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

    @PostMapping(value = "/friends")
    public void addFriend(@RequestParam(name = "idUser") Integer idUser, @RequestParam(name = "idUserFriend") Integer idUserFriend) {

        userService.addFriend(idUser, idUserFriend);
    }

    @DeleteMapping(value = "/friends")
    public void deleteFriend(@RequestParam(name = "idUser") Integer idUser, @RequestParam(name = "idUserFriend") Integer idUserFriend) {

        userService.deleteFriend(idUser, idUserFriend);
    }

    @GetMapping(value = "/friends/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Friend> findUserPrincipalFriendsByEmail(@PathVariable String email) {

        return userService.findFriendByPrincipalUserEmail(email);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(value = "/save")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {

         userService.saveUser(user);
         return "login";
    }


    @PutMapping(value = "/{id}")
    @ResponseBody
    public void updateUser(@PathVariable Integer id, @RequestBody User user) {

        userService.updateUser(id, user);
    }

}
