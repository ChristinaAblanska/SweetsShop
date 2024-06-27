package com.academy.cakeshop.persistance.entity;

import com.academy.cakeshop.enumeration.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "user_cake_store")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String lastName;

    @Column(name = "user_name", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String userName;

    @Column(name = "password", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String password;

    @Email
    @Column(name = "email", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String email;

    @Column(name = "phone_number")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String phoneNumber;

    @Column(name = "address")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Role role;
}