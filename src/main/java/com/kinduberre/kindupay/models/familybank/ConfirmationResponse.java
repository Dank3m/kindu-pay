package com.kinduberre.kindupay.models.familybank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationResponse {
    private String statusCode;
    private String statusDescription;
    private String paymentRef;
    private String dateTime;
}
