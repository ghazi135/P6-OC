package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    @Autowired
    private UserService userService;

}
