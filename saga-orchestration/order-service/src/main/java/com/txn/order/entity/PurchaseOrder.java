package com.txn.order.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.txn.enums.OrderStatus;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class PurchaseOrder {

	@Id
	private UUID id;
	private Integer userId;
	private Integer productId;
	private Double price;
	private OrderStatus status;

}