package com.switchwon.payment.domain.merchant;

import com.switchwon.payment.domain.Currency;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class FixedAmountFee {
    @NotNull
    private BigDecimal amount;

    @NotNull
    private Currency currency;
}
