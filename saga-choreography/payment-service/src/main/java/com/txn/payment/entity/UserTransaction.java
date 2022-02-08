package com.txn.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * 交易紀錄
 * 
 * */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UserTransaction {

    @Id
    private UUID orderId;
    private int userId;
    private int amount;

}
