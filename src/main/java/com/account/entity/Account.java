package com.account.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Entity
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Table(name = "ACCOUNT")
public class Account {

    @Id
    String accountId;
    String name;
    String accountType;
    BigDecimal balance;
    String phoneNumber;
    String email;
    String address;
    Boolean accountStatus;

}
