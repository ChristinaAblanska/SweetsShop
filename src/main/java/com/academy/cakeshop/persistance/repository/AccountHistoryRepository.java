package com.academy.cakeshop.persistance.repository;

import com.academy.cakeshop.persistance.entity.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {

}