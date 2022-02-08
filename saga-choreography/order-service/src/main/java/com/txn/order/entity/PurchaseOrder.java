package com.txn.order.entity;

import com.txn.events.inventory.InventoryStatus;
import com.txn.events.order.OrderStatus;
import com.txn.events.payment.PaymentStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.UUID;

/**
 * 購買訂單
 * 
 * */
@Data
@Entity
@ToString
public class PurchaseOrder {

    @Id
    private UUID id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private InventoryStatus inventoryStatus;

    /**
     * 使用樂觀鎖
     * 
     * */
    @Version
    private int version;

}