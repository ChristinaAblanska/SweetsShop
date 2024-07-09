package com.academy.cakeshop.service;

import com.academy.cakeshop.dto.BankAccountRequest;
import com.academy.cakeshop.dto.BankAccountResponse;
import com.academy.cakeshop.enumeration.BankAccountStatus;
import com.academy.cakeshop.errorHandling.BusinessNotFound;
import com.academy.cakeshop.errorHandling.FailedMoneyTransaction;
import com.academy.cakeshop.persistance.entity.BankAccount;
import com.academy.cakeshop.persistance.entity.User;
import com.academy.cakeshop.persistance.repository.BankAccountRepository;
import com.academy.cakeshop.enumeration.Currency;
import com.academy.cakeshop.persistance.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    public BankAccountResponse getByID(Long id) {
        BankAccount bankAccount = bankAccountRepository.getReferenceById(id);
        if (bankAccount != null) {
            String owner = bankAccount.getUser().getFirstName() + " " + bankAccount.getUser().getLastName();
            return new BankAccountResponse(bankAccount.getIban(),
                    bankAccount.getCurrency().toString(),
                    bankAccount.getBalance(), owner, bankAccount.getBankAccountStatus().toString());
        } else {
            throw new BusinessNotFound("BankAccount with id: " + id + "does not exist!");
        }
    }

    public BankAccountResponse getByUserID(Long userID) {
        BankAccount bankAccount = bankAccountRepository.findByUserID(userID);
        if (bankAccount != null) {
            String owner = bankAccount.getUser().getFirstName() + " " + bankAccount.getUser().getLastName();
            return new BankAccountResponse(bankAccount.getIban(),
                    bankAccount.getCurrency().toString(),
                    bankAccount.getBalance(), owner, bankAccount.getBankAccountStatus().toString());
        } else {
            throw new BusinessNotFound("BankAccount with userID: " + userID + " does not exist!");
        }

    }

    public List<BankAccountResponse> getAll() {
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        if (bankAccountList != null) {
            List<BankAccountResponse> bankAccountResponseList = new ArrayList<>();
            for (BankAccount bankAccount : bankAccountList) {
                String owner = bankAccount.getUser().getFirstName() + " " + bankAccount.getUser().getLastName();
                BankAccountResponse bankAccountResponse = new BankAccountResponse(bankAccount.getIban(),
                        bankAccount.getCurrency().toString(),
                        bankAccount.getBalance(), owner, bankAccount.getBankAccountStatus().toString());
                bankAccountResponseList.add(bankAccountResponse);
            }
            return bankAccountResponseList;
        } else {
            throw new BusinessNotFound("No bank accounts found!");
        }
    }

    public boolean existsByIBAN(String IBAN) {
        BankAccount bankAccount = bankAccountRepository.findByIbanEquals(IBAN);
        return bankAccount != null;
    }

    public void create(BankAccountRequest bankAccountRequest, String userName) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setIban(bankAccountRequest.iban());
        bankAccount.setBalance(bankAccountRequest.balance());
        bankAccount.setCurrency(Currency.getCurrencyFromString(bankAccountRequest.currency()));
        bankAccount.setBankAccountStatus(BankAccountStatus.ACTIVE);
        User user = userRepository.findByUserName(userName);
        bankAccount.setUser(user);
        bankAccountRepository.saveAndFlush(bankAccount);
    }

    public int updateBankAccountCurrency(Currency currency, Long userId) {
        BankAccount bankAccount = bankAccountRepository.findByUserID(userId);
        if (bankAccount != null) {
            return bankAccountRepository.updateCurrencyBy(currency, userId);
        } else {
            throw new BusinessNotFound("BankAccount with userID: " + userId + "does not exist!");
        }
    }

    public int updateBankAccountBalance(Double balance, Long userId) {
        BankAccount bankAccount = bankAccountRepository.findByUserID(userId);
        if (bankAccount != null) {
            return bankAccountRepository.updateBalanceBy(balance, userId);
        } else {
            throw new BusinessNotFound("BankAccount with userID: " + userId + "does not exist!");
        }
    }

    public void deleteByID(String iban) {
        if (existsByIBAN(iban)) {
            BankAccount bankAccount = bankAccountRepository.findByIbanEquals(iban);
            bankAccountRepository.deleteById(bankAccount.getId());
        } else {
            throw new BusinessNotFound("BankAccount with iban: " + iban + " does not exist!");
        }
    }

    public double getBalanceByIBAN(String iban) {
        if (existsByIBAN(iban)) {
            BankAccount bankAccount = bankAccountRepository.findByIbanEquals(iban);
            return bankAccount.getBalance();
        } else {
            throw new BusinessNotFound("BankAccount with iban: " + iban + "does not exist!");
        }
    }

    public BankAccount deposit(double sum, String iban) {
        if (existsByIBAN(iban)) {
            BankAccount bankAccount = bankAccountRepository.findByIbanEquals(iban);
            double balance = bankAccount.getBalance();
            sum = Math.abs(sum);
            balance = balance + sum;
            /*int rowsUpdated = */updateBankAccountBalance(balance, bankAccount.getId());
//            if (rowsUpdated > 0) {
                return bankAccount;
//            } else throw new FailedMoneyTransaction("Unable to complete the deposit!");
        } else {
            throw new BusinessNotFound("BankAccount with iban: " + iban + "does not exist!");
        }
    }

    public BankAccount withdraw(double sum, String iban) {
        if (existsByIBAN(iban)) {
            BankAccount bankAccount = bankAccountRepository.findByIbanEquals(iban);
            double balance = bankAccount.getBalance();
            sum = Math.abs(sum);
            if (sum <= balance) {
                balance = balance - sum;
                updateBankAccountBalance(balance, bankAccount.getId());
                return bankAccount;
            } else {
                throw new FailedMoneyTransaction("Unable to complete transaction due to low account balance!");
            }
        } else {
            throw new BusinessNotFound("BankAccount with iban: " + iban + "does not exist!");
        }
    }

    public int updateBankAccount(BankAccountRequest bankAccountRequest, Long userId) {
        int updatedRows = 0;
        if (!bankAccountRequest.currency().isEmpty()) {
            updatedRows += updateBankAccountCurrency(Currency.getCurrencyFromString(bankAccountRequest.currency()), userId);
        }
        return updatedRows;
    }
}