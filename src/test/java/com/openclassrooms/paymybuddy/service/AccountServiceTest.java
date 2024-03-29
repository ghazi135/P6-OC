package com.openclassrooms.paymybuddy.service;


import com.openclassrooms.paymybuddy.entity.BankAccount;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.repository.AccountRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {


    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    private AccountService accountService;

    @BeforeEach
    void setup() {

        accountService = new AccountService(accountRepository, userRepository);

    }

    @Test
    void findAllAccounts() {

        BankAccount       bankAccount     = new BankAccount(1, "111111", "44444", null);
        List<BankAccount> bankAccountList = new ArrayList<BankAccount>();
        bankAccountList.add(bankAccount);
        when(accountRepository.findAll()).thenReturn(bankAccountList);
        assertTrue(accountService.findAllAccounts().size() == 1);

    }

    @Test
    void findAllAccountById() {

        BankAccount bankAccount = new BankAccount(1, "111111", "44444", null);
        when(accountRepository.findById(1)).thenReturn(java.util.Optional.of(bankAccount));
        assertEquals(accountService.findAccountById(1).get().getAccountNumber(), "44444");
    }

    @Test
    void findAllAccountByEmail() {

        BankAccount bankAccount = new BankAccount(1, "111111", "44444", new User());
        when(accountRepository.findBankAccountByUserEmail(any(String.class))).thenReturn(bankAccount);
        Assertions.assertEquals(accountService.findAccountByEmail("").getAccountNumber(), "44444");
    }

    @Test
    void saveAccount() {

        User user = new User();
        user.setId(1);
        BankAccount bankAccount = new BankAccount(1, "gg4444", "111111", user);
        when(accountRepository.save(bankAccount)).thenReturn(bankAccount);
        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        accountService.saveBankAccount(1, bankAccount);
        verify(userRepository, times(1)).findById(1);
        verify(accountRepository, times(1)).save(bankAccount);


    }
}
