package com.txn.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 商品庫存
 * 
 * */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class OrderInventory {

    @Id
    private int productId;
    private int availableInventory;

}
