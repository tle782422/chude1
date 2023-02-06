package com.truyentranh.webtruyen.customers.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truyentranh.webtruyen.customers.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
