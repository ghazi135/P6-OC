package com.openclassrooms.paymybuddy.service;


import com.openclassrooms.paymybuddy.entity.BankAccount;
import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.repository.AccountRepository;
import com.openclassrooms.paymybuddy.repository.TransactionRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

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


    private TransactionService transactionService;

    @BeforeEach
    void setup() {

        transactionService = new TransactionService(transactionRepository, userRepository, accountService);
    }

    @Test
    void findAllTransactions() {

        Transaction       transaction     = new Transaction(1, "ghgg", 111.00, new User(), new User());
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        when(transactionRepository.findAll()).thenReturn(transactionList);

        Assertions.assertTrue(transactionService.findAllTransactions().size() > 0);
    }

    @Test
    void findAllTransactionByPrincipalUser() {

        User              user            = new User(1, "aaaa", "bbbb", "ggg@gggg", "111gg", 111.00, null, null,true,"USER");
        Transaction       transaction     = new Transaction(1, "ghgg", 111.00, new User(), new User());
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        when(transactionRepository.findTransactionByPayerUser_Email(user.getEmail())).thenReturn(transactionList);

        Assertions.assertTrue(transactionService.findTransactionsOfPrincipalUser(user).size() > 0);
    }

    @Test
    void sendMoneyExeptionTest() {

        User user  = new User(1, "aaaa", "bbbb", "ggg@gggg", "111gg", 111.00, null, null,true,"USER");
        User user1 = new User(2, "aaazzza", "bbbzzzb", "ggzzzg@gggg", "111zgg", 117771.00, null, null,true,"USER");
        when(userRepository.findUsersByEmail(user.getEmail())).thenReturn(user);
        when(userRepository.findUsersByEmail(user1.getEmail())).thenReturn(user1);

        Assertions.assertThrows(IllegalStateException.class, () -> {
            transactionService.sendMoney(user.getEmail(), 444.00, user1.getEmail(), "");
        });
    }

    @Test
    void sendMoneyNullableUserTest() {

        User user = new User(1, "aaaa", "bbbb", "ggg@gggg", "111gg", 111.00, null, null,true,"USER");
        when(userRepository.findUsersByEmail(user.getEmail())).thenReturn(user);

        Assertions.assertThrows(IllegalStateException.class, () -> {
            transactionService.sendMoney(user.getEmail(), 4444444.00, null, "");
        });
    }

    @Test
    void sendMoneyTest() {

        User user  = new User(1, "aaaa", "bbbb", "ggg@gggg", "111gg", 10000.00 + (10000.00 * 0.005), null, null,true,"USER");
        User user1 = new User(2, "aaazzza", "bbbzzzb", "ggzzzg@gggg", "111zgg", 0.00, null, null,true,"USER");
        when(userRepository.findUsersByEmail(user.getEmail())).thenReturn(user);
        when(userRepository.findUsersByEmail(user1.getEmail())).thenReturn(user1);
        transactionService.sendMoney(user.getEmail(), 10000.00, user1.getEmail(), "");

        Assertions.assertEquals(user.getMoneyAvailable(), 0.00);
        Assertions.assertEquals(user1.getMoneyAvailable(), 10000.00);


    }


    @Test
    void getBackMoneyInMyBankAccountTest() {

        User user = new User(1, "aaaa", "bbbb", "ggg@gggg", "111gg", 10000.00, null, null,true,"USER");
        when(userRepository.findUsersByEmail(user.getEmail())).thenReturn(user);
        transactionService.getBackMoneyOnMyBankAccount(user.getEmail(), 10000.00);
        Assertions.assertEquals(user.getMoneyAvailable(), 0.00);

    }

    @Test
    void rechargeMyBankAccountTest() {

        BankAccount bankAccount = new BankAccount(1, "111111", "44444", new User(1, "aaaa", "bbbb", "ggg@gggg", "111gg", 0.00, null, null,true,"USER"));
        User        user        = new User(1, "aaaa", "bbbb", "ggg@gggg", "111gg", 0.00, bankAccount, null,true,"USER");
        when(userRepository.findUsersByEmail(user.getEmail())).thenReturn(user);
        when(accountService.isAccorded(user.getBankAccount().getIBAN())).thenReturn(true);
        transactionService.rechargeApplicationAccount(user.getEmail(), 10000.00, user.getBankAccount().getIBAN());
        Assertions.assertEquals(user.getMoneyAvailable(), 10000.00);

    }


}
