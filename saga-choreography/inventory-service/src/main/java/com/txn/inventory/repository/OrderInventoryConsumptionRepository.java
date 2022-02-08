package com.txn.inventory.repository;

import com.txn.inventory.entity.OrderInventoryConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * 庫存消費紀錄
 * 
 * */
@Repository
public interface OrderInventoryConsumptionRepository extends JpaRepository<OrderInventoryConsumption, UUID> {
}
