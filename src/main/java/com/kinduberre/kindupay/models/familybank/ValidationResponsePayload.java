package com.kinduberre.kindupay.models.familybank;

import com.kinduberre.kindupay.models.common.enums.IdentifierType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponsePayload {
    private String identifier;
    private IdentifierType identifierType;
    private String customerId;
    private String customerName;
}
