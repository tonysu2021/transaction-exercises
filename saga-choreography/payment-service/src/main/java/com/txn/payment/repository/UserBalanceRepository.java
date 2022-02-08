package com.txn.payment.repository;

import com.txn.payment.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 使用者帳戶餘額
 * 
 * */
@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance, Integer> {
}
