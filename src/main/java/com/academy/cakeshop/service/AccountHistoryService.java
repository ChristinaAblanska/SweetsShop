package com.academy.cakeshop.service;

import com.academy.cakeshop.dto.AccountHistoryRequestDTO;
import com.academy.cakeshop.persistance.entity.AccountHistory;
import com.academy.cakeshop.persistance.entity.BankAccount;
import com.academy.cakeshop.persistance.repository.AccountHistoryRepository;
import com.academy.cakeshop.persistance.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AccountHistoryService {

    @Autowired
    private AccountHistoryRepository accountHistoryRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public AccountHistory createAccountHistory(AccountHistoryRequestDTO accountHistoryRequestDTO) {
        AccountHistory accountHistory = new AccountHistory();
        accountHistory.setDate(accountHistoryRequestDTO.getDate());

        BankAccount fromAccount = bankAccountRepository.findByIbanEquals(accountHistoryRequestDTO.getFromAccount());
        BankAccount toAccount = bankAccountRepository.findByIbanEquals(accountHistoryRequestDTO.getToAccount());
//
//        if (fromAccount == null) {
//            throw new IllegalArgumentException("Invalid fromAccount IBAN");
//        }
//
//        if (toAccount == null) {
//            throw new IllegalArgumentException("Invalid toAccount IBAN");
//        }

        accountHistory.setFromAccount(fromAccount);
        accountHistory.setToAccount(toAccount);
        accountHistory.setAmount(accountHistoryRequestDTO.getAmount());
        accountHistory.setCurrency(accountHistoryRequestDTO.getCurrency());

        return accountHistoryRepository.save(accountHistory);
    }
}//да питам защо toAccount и fromAccount изписват null
