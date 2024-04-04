package com.kinduberre.kindupay.services;

import com.kinduberre.kindupay.mappers.TransactionMapper;
import com.kinduberre.kindupay.models.core.Customer;
import com.kinduberre.kindupay.models.core.Transactions;
import com.kinduberre.kindupay.models.familybank.ConfirmationRequestPayload;
import com.kinduberre.kindupay.repositories.core.CustomerRepository;
import com.kinduberre.kindupay.repositories.core.TransactionsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionsRepository transactionsRepository;

    private final CustomerRepository customerRepository;

    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionsRepository transactionsRepository,
                              CustomerRepository customerRepository,
                              TransactionMapper transactionMapper) {
        this.transactionsRepository = transactionsRepository;
        this.customerRepository = customerRepository;
        this.transactionMapper = transactionMapper;
    }

    public Transactions saveTransaction(ConfirmationRequestPayload confirmationRequest) {
        Transactions transaction = transactionMapper.confirmationRequestToTransaction(confirmationRequest);

        //Convert to LocalDateTime so that the entity can be parsed to the database
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        transaction.setTranDate(LocalDateTime.parse(confirmationRequest.getDateTime(), formatter));

        transaction.setTranType("COLLECTIONS");
        Optional<Customer> customer = customerRepository.findByCustRef(confirmationRequest.getCustomerId());
        customer.ifPresent(transaction::setCustomer);
        System.out.println("There");
        transaction = transactionsRepository.save(transaction);
        System.out.println("Here");
        return transaction;
    }
}
