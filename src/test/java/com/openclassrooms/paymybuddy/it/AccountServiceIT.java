package com.openclassrooms.paymybuddy.it;


import com.openclassrooms.paymybuddy.entity.BankAccount;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.repository.AccountRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;


@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Import(AccountService.class)
public class AccountServiceIT {


    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository    userRepository;
    @Autowired
    private final AccountService accountService = new AccountService(accountRepository, userRepository);
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void injectedComponentsAreRightlySetUp() {

        Assertions.assertNotNull(accountService);
        Assertions.assertNotNull(testEntityManager);
    }

    @Test
    public void findAllAccountsIT(){

        BankAccount       bankAccount     = new BankAccount(1, "111111", "44444", null);
        accountRepository.save(bankAccount);
        Assertions.assertTrue(accountService.findAllAccounts().get(0).getIBAN().contains("111111"));
    }

    @Test
    public void findAllAccountByIdIT(){

        BankAccount       bankAccount     = new BankAccount(1, "111111", "44444", null);
        accountRepository.save(bankAccount);
        Assertions.assertTrue(accountService.findAccountById(1).get().getIBAN().contains("111111"));
    }

    @Test
    public void findAllAccountByEmailIT(){
        User user = new User();
        user.setEmail("gggg@gggg");
        userRepository.save(user);
        BankAccount       bankAccount     = new BankAccount(1, "111111", "44444", user);
        accountRepository.save(bankAccount);

        Assertions.assertTrue(accountService.findAccountByEmail(user.getEmail()).getIBAN().contains("111111"));
    }

    @Test
    public void saveBankAccountIT(){
        User user = new User();
        user.setId(1);
        user.setEmail("gggg@gggg");
        userRepository.save(user);
        BankAccount       bankAccount     = new BankAccount(1, "111111", "44444", user);
        accountService.saveBankAccount(user.getId(),bankAccount);

        Assertions.assertTrue(accountService.findAccountByEmail(user.getEmail()).getIBAN().contains("111111"));
    }

    @Test
    public void deleteBankAccountIT(){
        User user = new User();
        user.setId(1);
        user.setEmail("gggg@gggg");
        userRepository.save(user);
        BankAccount       bankAccount     = new BankAccount(1, "111111", "44444", user);
        accountService.saveBankAccount(user.getId(),bankAccount);

        Assertions.assertTrue(accountService.findAccountByEmail(user.getEmail()).getIBAN().contains("111111"));
        accountService.deleteAccount(bankAccount.getId());
        Assertions.assertTrue(accountService.findAccountById(1).isEmpty());

    }

}
