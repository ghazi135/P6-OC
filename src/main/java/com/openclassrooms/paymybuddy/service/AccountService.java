package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.BankAccount;
import com.openclassrooms.paymybuddy.repository.AccountRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Log4j2
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final UserRepository    userRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {

        this.accountRepository = accountRepository;
        this.userRepository    = userRepository;
    }


    public List<BankAccount> findAllAccounts() {
        log.info("<-----------find all accounts");

        return accountRepository.findAll();
    }

    public Optional<BankAccount> findAccountById(int id) {
        log.info("<-----------find account by id");


        return accountRepository.findById(id);
    }

    public BankAccount findAccountByEmail(String email) {
        log.info("<-----------find account by user email");

        return accountRepository.findBankAccountByUserEmail(email);
    }

    public BankAccount saveBankAccount(Integer idUser, BankAccount bankAccount) {
        log.info("<-----------save bank account to user");

        bankAccount.setUser(userRepository.findById(idUser).get());
        return accountRepository.save(bankAccount);

    }

    public void deleteAccount(Integer id) {
        log.info("<-----------delete account");

        accountRepository.deleteById(id);

    }

    public boolean isAccorded(String iban) {

        BankAccount bankAccount = accountRepository.findBankAccountByIBAN(iban);

        if (bankAccount.getIBAN().equals(iban)) {
            log.info("<-----------iban found");

            return true;
        } else {
            log.error("<-----------Bank Account Not Found");

            throw new IllegalArgumentException("Bank Account Not Found");
        }
    }
}
