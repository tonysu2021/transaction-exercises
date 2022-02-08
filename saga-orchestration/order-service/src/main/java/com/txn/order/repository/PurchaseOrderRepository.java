package com.txn.order.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.txn.order.entity.PurchaseOrder;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, UUID> {
}
