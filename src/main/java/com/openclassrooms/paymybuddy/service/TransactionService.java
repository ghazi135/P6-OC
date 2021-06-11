package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.entity.User;
import com.openclassrooms.paymybuddy.repository.TransactionRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private final TransactionRepository transactionRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final AccountService accountService;

    private Transaction newTransaction;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, AccountService accountService) {

        this.transactionRepository = transactionRepository;
        this.userRepository        = userRepository;
        this.accountService        = accountService;
    }


    public List<Transaction> findAllTransactions() {

        return transactionRepository.findAll();
    }

    public List<Transaction> findTransactionsOfPrincipalUser(User user) {

        List<Transaction> payerTransactionList = transactionRepository.findTransactionByPayerUser_Email(user.getEmail());
        List<Transaction> beneficiaryTransactionList = transactionRepository.findTransactionByBeneficiaryUser_Email(user
                .getEmail());

        List<Transaction> combinedList = Stream.of(payerTransactionList, beneficiaryTransactionList)
                                               .flatMap(x -> x.stream())
                                               .collect(Collectors.toList());
        return combinedList;
    }

    public void sendMoney(String emailSender, Double amount, String EmailReceiver, String description) {

        User userSender   = userRepository.findUsersByEmail(emailSender);
        User userReceiver = userRepository.findUsersByEmail(EmailReceiver);

        if (userSender.getMoneyAvailable() - ((amount) + (amount * 0.005)) < 0) {
            throw new IllegalStateException("Solde insuffisant");
        } else if (userReceiver == null) {
            throw new IllegalStateException("user Receiver introuvable");
        } else {
            userSender.setMoneyAvailable(userSender.getMoneyAvailable() - ((amount) + (amount * 0.005)));
            userReceiver.setMoneyAvailable(userReceiver.getMoneyAvailable() + amount);
            userRepository.save(userSender);
            userRepository.save(userReceiver);
            newTransaction = new Transaction();
            newTransaction.setAmount(amount);
            newTransaction.setPayerUser(userSender);
            newTransaction.setBeneficiaryUser(userReceiver);
            newTransaction.setDescription(description);
            transactionRepository.save(newTransaction);
        }

    }

    //    public void sendMoney(User user , Transactiondto transactiondto) {
    //
    //        User userSender   = userRepository.findUsersByEmail(user.getEmail());
    //        User userReceiver = userRepository.findUsersByEmail(transactiondto.getEmailReceiver().getEmail());
    //
    //        if (user.getMoneyAvailable() - ((transactiondto.getAmount()) + (transactiondto.getAmount() * 0.005)) < 0) {
    //            throw new IllegalStateException("Solde insuffisant");
    //        } else if (transactiondto.getEmailReceiver() == null) {
    //            throw new IllegalStateException("user Receiver introuvable");
    //        } else {
    //            userSender.setMoneyAvailable(userSender.getMoneyAvailable() - (((transactiondto.getAmount()) + ((transactiondto.getAmount() * 0.005)))));
    //            userReceiver.setMoneyAvailable(transactiondto.getEmailReceiver().getMoneyAvailable() + (transactiondto.getAmount()));
    //            userRepository.save(user);
    //            userRepository.save(transactiondto.getEmailReceiver());
    //            newTransaction = new Transaction();
    //            newTransaction.setAmount(transactiondto.getAmount());
    //            newTransaction.setPayerUser(user);
    //            newTransaction.setBeneficiaryUser(transactiondto.getEmailReceiver());
    //            newTransaction.setDescription(transactiondto.getDescription());
    //            transactionRepository.save(newTransaction);
    //        }
    //
    //    }

    public void getBackMoneyOnMyBankAccount(String emailUser, Double amount) {

        User user = userRepository.findUsersByEmail(emailUser);
        if (user.getMoneyAvailable() < amount) {
            throw new IllegalStateException("Solde insuffisant");
        } else {
            user.setMoneyAvailable(user.getMoneyAvailable() - amount);
            userRepository.save(user);
        }
    }

    public void rechargeApplicationAccount(String emailUser, Double amount, String iban) {

        User user = userRepository.findUsersByEmail(emailUser);
        if (accountService.isAccorded(iban)) {
            user.setMoneyAvailable(user.getMoneyAvailable() + amount);
        } else {
            throw new IllegalStateException("rechargement non accordé");

        }
    }


}
