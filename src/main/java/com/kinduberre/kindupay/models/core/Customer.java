package com.kinduberre.kindupay.models.core;

import com.kinduberre.kindupay.models.auth.User;
import com.kinduberre.kindupay.models.common.enums.IdentifierType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Long custId;
    private String custName;
    private String custRef;
    private IdentifierType identifierType;
    private Company company;
    private User createdBy;
    private LocalDate createdDate;
    private User modifiedBy;
    private LocalDate modifiedDate;
}
