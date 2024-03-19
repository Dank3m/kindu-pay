package com.kinduberre.kindupay.models.core;

import com.kinduberre.kindupay.models.auth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private Long compId;
    private String compName;
    private String compEmail;
    private String compPhone;
    private int Status;
    private User createdBy;
    private LocalDate createdDate;
    private User modifiedBy;
    private LocalDate modifiedDate;
}
