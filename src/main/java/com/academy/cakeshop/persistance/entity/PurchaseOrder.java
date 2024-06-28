package com.academy.cakeshop.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import com.academy.cakeshop.enumeration.BankAccountStatus;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "purchaseOrderID", nullable = false)
    private Long id;

    @NotNull(message = "Quantity is mandatory")
    @Positive(message = "Quantity must be positive")
    @Column(name="quantity", nullable=false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private int quantity;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive")
    @Column(name="price", nullable=false)
    @JdbcTypeCode(SqlTypes.DECIMAL)
    private double price;

    @NotNull(message = "Status is mandatory")
    @Enumerated(EnumType.STRING)
     @Column(name="status",nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private BankAccountStatus bankAccountStatus;

    @NotNull(message = "Date is mandatory")
    @Column(name = "date", nullable = false)
    @JdbcTypeCode(SqlTypes.DATE)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="productID",referencedColumnName = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name="unitID",referencedColumnName = "unitID")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name="contractID",referencedColumnName = "contractID")
    private Contract contract;


}