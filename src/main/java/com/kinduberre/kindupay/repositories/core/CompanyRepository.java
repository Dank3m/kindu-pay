package com.kinduberre.kindupay.repositories.core;

import com.kinduberre.kindupay.models.core.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
