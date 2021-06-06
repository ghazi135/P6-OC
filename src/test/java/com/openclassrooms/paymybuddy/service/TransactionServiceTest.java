package com.openclassrooms.paymybuddy.service;


import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.repository.AccountRepository;
import com.openclassrooms.paymybuddy.repository.TransactionRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {


    @Mock
    UserRepository userRepository;

    @Mock
    AccountRepository accountRepository;

    @Mock
    AccountService accountService = new AccountService(accountRepository, userRepository);

    @Mock
    TransactionRepository transactionRepository;

    private Transaction transaction;

    private final TransactionService transactionService = new TransactionService(transactionRepository, userRepository, accountService);

    @BeforeEach
    void setup() {

    }


}
