package com.txn.payment.repository;

import com.txn.payment.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * 交易紀錄
 * 
 * */
@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction, UUID> {
}
