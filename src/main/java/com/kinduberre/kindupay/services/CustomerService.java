package com.kinduberre.kindupay.services;

import com.kinduberre.kindupay.models.core.Customer;
import com.kinduberre.kindupay.repositories.core.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> findCustomerByCustRef (String custRef)
    {
        return customerRepository.findByCustRef(custRef);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
