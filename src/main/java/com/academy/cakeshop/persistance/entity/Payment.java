package com.academy.cakeshop.persistance.entity;

import com.academy.cakeshop.enumeration.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "date", nullable = false)
    @JdbcTypeCode(SqlTypes.DATE)
    private LocalDate date;

    // TODO how to validate the contract?
    @ManyToOne(optional = false)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    // TODO how to validate the account from the db?
    @ManyToOne(optional = false)
    @JoinColumn(name = "bank_account_id", nullable = false)
    // TODO how to name the column in the new table???
    private BankAccount fromBankAccount;

//    // TODO how to validate the account from the db?
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "bank_account_id", nullable = false)
//    // TODO how to name the column in the new table???
//    private BankAccount toBankAccount;

    @Column(name = "amount", nullable = false)
    @JdbcTypeCode(SqlTypes.DECIMAL)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Currency currency;
}