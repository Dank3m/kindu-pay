package com.kinduberre.kindupay.controllers.familybank;

import com.kinduberre.kindupay.models.core.Customer;
import com.kinduberre.kindupay.models.core.Transactions;
import com.kinduberre.kindupay.models.familybank.*;
import com.kinduberre.kindupay.services.CustomerService;
import com.kinduberre.kindupay.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fbl")
public class CollectionController {


    private final CustomerService customerService;
    private final TransactionService transactionService;
    public CollectionController(CustomerService customerService,
                                TransactionService transactionService)
    {
        this.customerService = customerService;
        this.transactionService = transactionService;
    }



    //Validation
    @PreAuthorize("hasAuthority('FBL_COLLECTIONS')")
    @PostMapping("/validation")
    public ResponseEntity<ValidationResponse> validateClient(@RequestBody ValidationRequest validationRequest) {
        Optional<Customer> customer = customerService.findCustomerByCustRef(validationRequest.getPayload().getIdentifier());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ValidationResponse validationResponse = new ValidationResponse();

        if(customer.isPresent()) {

            // Setting outer body
            validationResponse.setStatusCode("ACCOUNT_FOUND");
            validationResponse.setStatusDescription("ACCOUNT IS VALID");
            validationResponse.setDateTime(LocalDateTime.now().format(formatter));
            // Setting inner body
            validationResponse.getPayload().setCustomerId(String.valueOf(customer.get().getCustId()));
            validationResponse.getPayload().setIdentifier(customer.get().getCustRef());
            validationResponse.getPayload().setIdentifierType(customer.get().getIdentifierType());
            validationResponse.getPayload().setCustomerName(customer.get().getCustName());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(validationResponse);
        } else {
            validationResponse.setStatusCode("ACCOUNT_NOT_FOUND");
            validationResponse.setStatusDescription("ACCOUNT IS NOT VALID");
            validationResponse.setDateTime(LocalDateTime.now().format(formatter));

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(validationResponse);
        }
    }

    @PreAuthorize("hasAuthority('FBL_COLLECTIONS')")
    @PostMapping("/confirmation")
    public ResponseEntity<ConfirmationResponse> confirmTransaction(@RequestBody ConfirmationRequest confirmationRequest) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);

        ConfirmationResponse confirmationResponse = new ConfirmationResponse();
        try {
            Transactions transaction = transactionService.saveTransaction(confirmationRequest.getPayload());
            confirmationResponse.setDateTime(formattedDateTime);
            confirmationResponse.setPaymentRef(String.valueOf(transaction.getTranId()));
            confirmationResponse.setStatusCode("PAYMENT_ACK");
            confirmationResponse.setStatusDescription("Payment Transaction Received Successfully");
        }
        catch (Exception exception) {
            confirmationResponse.setDateTime(formattedDateTime);
            confirmationResponse.setPaymentRef(confirmationRequest.getPayload().getTxnReference());
            confirmationResponse.setStatusCode("PAYMENT_NACK");
            confirmationResponse.setStatusDescription("Payment Transaction not Received");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(confirmationResponse);
    }
}
