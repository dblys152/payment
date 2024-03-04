package com.switchwon.payment.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceModel {
    String userId;
    BigDecimal balance;
    String currency;
}
