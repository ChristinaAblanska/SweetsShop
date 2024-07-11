package com.academy.cakeshop.service;

import com.academy.cakeshop.dto.AccountHistoryRequestDTO;
import com.academy.cakeshop.persistance.entity.AccountHistory;
import com.academy.cakeshop.persistance.entity.BankAccount;
import com.academy.cakeshop.persistance.repository.AccountHistoryRepository;
import com.academy.cakeshop.persistance.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class AccountHistoryService {

    private AccountHistoryRepository accountHistoryRepository;
    private BankAccountRepository bankAccountRepository;

    public AccountHistory createAccountHistory(AccountHistoryRequestDTO accountHistoryRequestDTO) {
        AccountHistory accountHistory = new AccountHistory();
        accountHistory.setDate(accountHistoryRequestDTO.getDate());

        BankAccount fromAccount = bankAccountRepository.findByIbanEquals(accountHistoryRequestDTO.getFromAccount());
        BankAccount toAccount = bankAccountRepository.findByIbanEquals(accountHistoryRequestDTO.getToAccount());


        accountHistory.setFromAccount(fromAccount);
        accountHistory.setToAccount(toAccount);
        accountHistory.setAmount(accountHistoryRequestDTO.getAmount());
        accountHistory.setCurrency(accountHistoryRequestDTO.getCurrency());

        return accountHistoryRepository.save(accountHistory);

}
    public List<AccountHistory> getAllAccountHistories() {
        return accountHistoryRepository.findAll();
    }

    public Optional<AccountHistory> getAccountHistoryById(Long id) {
        return accountHistoryRepository.findById(id);
    }

    public AccountHistory updateAccountHistory(Long id, AccountHistoryRequestDTO accountHistoryRequestDTO) {
        Optional<AccountHistory> accountHistoryOptional = accountHistoryRepository.findById(id);
        if (accountHistoryOptional.isPresent()) {
            AccountHistory accountHistory = accountHistoryOptional.get();
            accountHistory.setDate(accountHistoryRequestDTO.getDate());

            BankAccount fromAccount = bankAccountRepository.findByIbanEquals(accountHistoryRequestDTO.getFromAccount());
            BankAccount toAccount = bankAccountRepository.findByIbanEquals(accountHistoryRequestDTO.getToAccount());

            accountHistory.setFromAccount(fromAccount);
            accountHistory.setToAccount(toAccount);
            accountHistory.setAmount(accountHistoryRequestDTO.getAmount());
            accountHistory.setCurrency(accountHistoryRequestDTO.getCurrency());

            return accountHistoryRepository.save(accountHistory);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHistory not found");
        }
    }

    public void deleteAccountHistory(Long id) {
        Optional<AccountHistory> accountHistoryOptional = accountHistoryRepository.findById(id);
        if (accountHistoryOptional.isPresent()) {
            accountHistoryRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHistory not found");
        }
    }
}
