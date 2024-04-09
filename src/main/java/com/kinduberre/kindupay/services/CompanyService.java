package com.kinduberre.kindupay.services;

import com.kinduberre.kindupay.models.core.Company;
import com.kinduberre.kindupay.repositories.core.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }
}
