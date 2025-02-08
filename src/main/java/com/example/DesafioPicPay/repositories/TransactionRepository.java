package com.example.DesafioPicPay.repositories;

import com.example.DesafioPicPay.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
