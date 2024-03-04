package com.switchwon.payment.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEstimationModel {
    BigDecimal estimatedTotal;
    BigDecimal fees;
    String currency;
}
