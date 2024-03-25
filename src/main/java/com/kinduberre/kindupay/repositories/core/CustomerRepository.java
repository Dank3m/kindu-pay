package com.kinduberre.kindupay.repositories.core;

import com.kinduberre.kindupay.models.core.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
