package com.kinduberre.kindupay.repositories.core;

import com.kinduberre.kindupay.models.core.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
}
