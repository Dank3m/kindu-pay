package com.kinduberre.kindupay.models.dtos.familybank;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private String fullName;
    private String clientId;
    private String clientSecret;
}
