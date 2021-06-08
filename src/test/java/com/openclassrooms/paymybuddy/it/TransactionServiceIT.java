package com.openclassrooms.paymybuddy.it;


import com.openclassrooms.paymybuddy.entity.BankAccount;
import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.repository.AccountRepository;
import com.openclassrooms.paymybuddy.repository.TransactionRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.AccountService;
import com.openclassrooms.paymybuddy.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Import({TransactionService.class, AccountService.class})
public class TransactionServiceIT {

    @Autowired
    private       AccountRepository     accountRepository;
    @Autowired
    private       UserRepository        userRepository;
    @Autowired
    private final AccountService        accountService     = new AccountService(accountRepository, userRepository);
    @Autowired
    private       TransactionRepository transactionRepository;
    @Autowired
    private final TransactionService    transactionService = new TransactionService(transactionRepository, userRepository, accountService);
    @Autowired
    private       TestEntityManager     testEntityManager;

    @Test
    public void injectedComponentsAreRightlySetUp() {

        Assertions.assertNotNull(accountService);
        Assertions.assertNotNull(transactionService);
        Assertions.assertNotNull(testEntityManager);
    }

    @Test
    void findAllTransactions() {

        Transaction transaction = new Transaction(1, "ghgg", 111.00, null, null);
        transactionRepository.save(transaction);
        Assertions.assertFalse(transactionService.findAllTransactions().isEmpty());
    }

    @Test
    void findAllTransactionByPrincipalUser() {

        User        user        = new User(1, "aaaa", "bbbb", "ggg@gggg", "111gg", 111.00, null, null,true,"USER");
        Transaction transaction = new Transaction(1, "ghgg", 111.00, null, user);
        user.setBankAccount(new BankAccount());
        userRepository.save(user);
        transactionRepository.save(transaction);
        Assertions.assertFalse(transactionService.findTransactionsOfPrincipalUser(user).isEmpty());
    }


    @Test
    void sendMoneyTest() {

        User user  = new User(1, "aaaa", "bbbb", "ggg@gggg", "111gg", 10000.00 + (10000.00 * 0.005), null, null,true,"USER");
        User user1 = new User(2, "aaazzza", "bbbzzzb", "ggzzzg@gggg", "111zgg", 0.00, null, null,true,"USER");
        userRepository.save(user);
        userRepository.save(user1);

        transactionService.sendMoney(user.getEmail(), 10000.00, user1.getEmail(), "");

        Assertions.assertEquals(userRepository.findById(1).get().getMoneyAvailable(), 0.00);
        Assertions.assertEquals(userRepository.findById(2).get().getMoneyAvailable(), 10000.00);

    }

    @Test
    void getBackMoneyInMyBankAccountTest() {

        User user = new User(1, "aaaa", "bbbb", "ggg@gggg", "111gg", 10000.00, null, null,true,"USER");
        userRepository.save(user);
        transactionService.getBackMoneyOnMyBankAccount(user.getEmail(), 10000.00);
        Assertions.assertEquals(userRepository.findById(user.getId()).get().getMoneyAvailable(), 0.00);

    }

    @Test
    void rechargeMyBankAccountTest() {

        BankAccount bankAccount = new BankAccount(1, "111111", "44444", new User(1, "aaaa", "bbbb", "ggg@gggg", "111gg", 0.00, null, null,true,"USER"));
        User        user        = new User(1, "aaaa", "bbbb", "ggg@gggg", "111gg", 0.00, bankAccount, null,true,"USER");
        user.setBankAccount(bankAccount);
        bankAccount.setUser(user);
        userRepository.save(user);
        accountRepository.save(bankAccount);
        transactionService.rechargeApplicationAccount(user.getEmail(), 10000.00, user.getBankAccount().getIBAN());
        Assertions.assertEquals(userRepository.findById(user.getId()).get().getMoneyAvailable(), 10000.00);

    }
}
