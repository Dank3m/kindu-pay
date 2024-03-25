package com.kinduberre.kindupay.repositories.core;

import com.kinduberre.kindupay.models.core.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
