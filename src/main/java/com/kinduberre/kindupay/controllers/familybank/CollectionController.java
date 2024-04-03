package com.kinduberre.kindupay.controllers.familybank;

import com.kinduberre.kindupay.models.core.Customer;
import com.kinduberre.kindupay.models.familybank.*;
import com.kinduberre.kindupay.services.CustomerService;
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
    public CollectionController(CustomerService customerService) {
        this.customerService = customerService;
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
    public ConfirmationResponse confirmTransaction(@RequestBody ConfirmationRequest confirmationRequest) {
        return new ConfirmationResponse();
    }
}
