package com.kinduberre.kindupay.models.familybank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationRequestPayload {
    private String customerId;
    private String payerName;
    private String payerPhone;
    private Double txnAmount;
    private String paymentMode;
    private String txnReference;
    private String collectionAccount;
    private String txnNarration;
    private String dateTime;
}
