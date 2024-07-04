package com.academy.cakeshop.persistance.repository;

import com.academy.cakeshop.persistance.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
}

