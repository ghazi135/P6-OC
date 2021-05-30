package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.BankAccount;
import com.openclassrooms.paymybuddy.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;


    public List<BankAccount> findAllAccounts() {

        return accountRepository.findAll();
    }

    public Optional<BankAccount> findAccountById(int id) {


        return accountRepository.findById(id);
    }

    public BankAccount findAccountByEmail(String email) {

        return accountRepository.findBankAccountByUserEmail(email);
    }

    public void saveBankAccount(BankAccount bankAccount) {

        accountRepository.save(bankAccount);
    }

    public void UpdateBankAccount(Integer id, BankAccount bankAccount) {

        BankAccount account = accountRepository.getById(id);
        account = bankAccount;
        accountRepository.save(account);
    }
}
