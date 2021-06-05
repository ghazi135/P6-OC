package com.openclassrooms.paymybuddy.service;


import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    @Autowired
    TransactionRepository transactionRepository;

    private Transaction transaction;

    private TransactionService transactionService;

    @BeforeEach
    void setup() {

    }

}
