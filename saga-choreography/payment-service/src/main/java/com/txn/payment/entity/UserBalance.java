package com.txn.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 使用者帳戶餘額
 * 
 * */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserBalance {

    @Id
    private int userId;
    private int balance;

}
