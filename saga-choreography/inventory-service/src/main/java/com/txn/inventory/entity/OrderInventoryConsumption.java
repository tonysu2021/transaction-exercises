package com.txn.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * 庫存消費紀錄
 * 
 * */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class OrderInventoryConsumption {

    @Id
    private UUID orderId;
    private int productId;
    private int quantityConsumed;

}
