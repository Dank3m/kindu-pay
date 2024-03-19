package com.kinduberre.kindupay.models.familybank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponse {
    private String statusCode;
    private String statusDescription;
    private String dateTime;
    private ValidationResponsePayload payload;
}
