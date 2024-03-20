package com.kinduberre.kindupay.controllers.familybank;

import com.kinduberre.kindupay.models.familybank.ConfirmationRequest;
import com.kinduberre.kindupay.models.familybank.ConfirmationResponse;
import com.kinduberre.kindupay.models.familybank.ValidationRequest;
import com.kinduberre.kindupay.models.familybank.ValidationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class CollectionController {

    //Validation
    @PostMapping("/familybank/validation")
    public ValidationResponse validateClient(@RequestBody ValidationRequest validationRequest) {
        return new ValidationResponse();
    }

    @PostMapping("/familybank/confirmation")
    public ConfirmationResponse confirmTransaction(@RequestBody ConfirmationRequest confirmationRequest) {
        return new ConfirmationResponse();
    }
}
