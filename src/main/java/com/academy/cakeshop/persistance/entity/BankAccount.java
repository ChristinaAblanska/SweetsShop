package com.academy.cakeshop.persistance.entity;

import com.academy.cakeshop.enumeration.BankAccountStatus;
import com.academy.cakeshop.enumeration.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "iban", nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String iban;

    @Column(name = "balance", nullable = false)
    @JdbcTypeCode(SqlTypes.DECIMAL)
    private Double balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank_account_status", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private BankAccountStatus bankAccountStatus;

    // TODO: How to validate the user owner of the account?
    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(nullable = false, unique = true)
    private User user;
}