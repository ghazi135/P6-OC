package com.openclassrooms.paymybuddy.it;


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

}
