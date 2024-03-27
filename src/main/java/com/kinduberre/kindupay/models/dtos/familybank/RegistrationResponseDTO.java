package com.kinduberre.kindupay.models.dtos.familybank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponseDTO {
    private String fullName;
    private String clientId;
}
