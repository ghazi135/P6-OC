package com.openclassrooms.paymybuddy.service;


import com.openclassrooms.paymybuddy.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {


    @Mock
    @Autowired
    private AccountRepository accountRepository;

    private AccountService accountService;
    @BeforeEach
    void setup() {

    }
}
