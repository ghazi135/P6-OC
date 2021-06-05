package com.openclassrooms.paymybuddy.controller;


import com.openclassrooms.paymybuddy.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @MockBean
    @Autowired
    private TransactionService transactionService;

    @BeforeEach
    void setup() {

    }
}
