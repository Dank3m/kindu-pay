package com.kinduberre.kindupay.models.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {
    private Long tranId;
    private String bankTranRef;
    private Double tranAmount;
    private String bankAcct;
    private Company company;
    private Customer customer;
    private LocalDate tranDate;
    private String payerName;
    private String payerPhone;
    private String paymentMode;
    private String narration;
}
