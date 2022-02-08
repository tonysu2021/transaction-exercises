package com.txn.order.repository;

import com.txn.order.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * 購買訂單
 * 
 * */
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, UUID> {
}
