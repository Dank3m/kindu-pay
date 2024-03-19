package com.kinduberre.kindupay.models.core;

import com.kinduberre.kindupay.models.common.enums.Bank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
    private Long acctId;
    private String accountNo;
    private Bank bank;
    private Company company;
    private Double acctBal;
}
