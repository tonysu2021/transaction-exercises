package com.txn.inventory.repository;

import com.txn.inventory.entity.OrderInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 商品庫存
 * 
 * */
@Repository
public interface OrderInventoryRepository extends JpaRepository<OrderInventory, Integer> {
}
