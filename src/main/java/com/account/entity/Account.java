package com.account.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Table(name = "ACCOUNT")
@Entity
public class Account {

    @Id
    @Column(name = "ACCOUNT_ID")
    String accountId;
    @Column(name = "NAME")
    String name;
    @Column(name = "ACCOUNT_TYPE")
    String accountType;
    @Column(name = "BALANCE")
    BigDecimal balance;
    @Column(name = "PHONE_NUMBER")
    String phoneNumber;
    @Column(name = "EMAIL")
    String email;
    @Column(name = "ADDRESS")
    String address;
    @Column(name = "ACCOUNT_STATUS")
    String accountStatus;

}
