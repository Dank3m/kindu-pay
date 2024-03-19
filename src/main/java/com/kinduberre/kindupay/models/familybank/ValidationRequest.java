package com.kinduberre.kindupay.models.familybank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationRequest {
    private String action;
    private ValidationRequestPayload payload;
}
