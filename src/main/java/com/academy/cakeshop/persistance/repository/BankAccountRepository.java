package com.academy.cakeshop.persistance.repository;

import com.academy.cakeshop.enumeration.Currency;
import com.academy.cakeshop.persistance.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    @Transactional
    @Modifying
    @Query("update BankAccount b set b.currency = ?1")
    int updateCurrencyBy(Currency currency);

    @Transactional
    @Modifying
    @Query("update BankAccount b set b.balance = ?1 where b.id = ?2")
    int updateBalanceBy(Double balance, Long id);

    @Query("select b from BankAccount b where b.iban = ?1")
    BankAccount findByIbanEquals(String iban);

    @Query("select b from BankAccount b where b.user.id = ?1")
    BankAccount findByUserID(Long id);
}