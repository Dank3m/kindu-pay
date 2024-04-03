package com.kinduberre.kindupay.services;

import com.kinduberre.kindupay.models.core.Transactions;
import com.kinduberre.kindupay.models.familybank.ConfirmationRequest;
import com.kinduberre.kindupay.repositories.core.TransactionsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TransactionService {
    private final TransactionsRepository transactionsRepository;

    private final

    public TransactionService(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public Transactions saveTransaction(ConfirmationRequest confirmationRequest) {
        Transactions transaction = new Transactions();

        //Convert to LocalDateTime so that the entity can be parsed to the database
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        transaction.setTranDate(LocalDateTime.parse(confirmationRequest.getPayload().getDateTime(), formatter));

        transaction.setPayerName(confirmationRequest.getPayload().getPayerName());
        transaction.setPayerPhone(confirmationRequest.getPayload().getPayerPhone());
        transaction.setTranAmount(confirmationRequest.getPayload().getTxnAmount());
        transaction.setPaymentMode(confirmationRequest.getPayload().getPaymentMode());
        transaction.setBankTranRef(confirmationRequest.getPayload().getTxnReference());
        transaction.setBankAcct(confirmationRequest.getPayload().getCollectionAccount());
        transaction.setNarration(confirmationRequest.getPayload().getTxnNarration());
        transaction.setTranType("COLLECTIONS");

        return transaction;
    }
}
