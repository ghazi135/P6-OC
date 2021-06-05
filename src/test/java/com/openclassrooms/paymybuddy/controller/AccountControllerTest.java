package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(BankAccountController.class)
public class AccountControllerTest {

    @MockBean
    @Autowired
    private AccountService accountService;

    @BeforeEach
    void setup() {

    }
}
