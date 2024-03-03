package com.switchwon.payment.domain.estimation;

import com.switchwon.payment.domain.Currency;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "create")
public class PaymentEstimation {
    BigDecimal amount;
    Currency currency;
    BigDecimal estimatedTotal;
    BigDecimal fees;
}
