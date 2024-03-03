package com.switchwon.payment.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentEstimationModel {
    BigDecimal estimatedTotal;
    BigDecimal fees;
    String currency;
}
