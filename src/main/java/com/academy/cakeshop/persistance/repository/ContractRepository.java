package com.academy.cakeshop.persistance.repository;

import com.academy.cakeshop.enumeration.ContractStatus;
import com.academy.cakeshop.persistance.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Transactional
    @Modifying
    @Query("update Contract c set c.contractStatus = ?1")
    int updateContractStatusBy(ContractStatus contractStatus);

    @Query("select c from Contract c where c.user.id = ?1")
    Contract findByUserID(Long id);
}