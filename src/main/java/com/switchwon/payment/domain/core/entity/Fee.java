package com.switchwon.payment.domain.core.entity;

import com.switchwon.payment.domain.Currency;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class Fee {
    @NotNull
    FeeType type;

    @NotNull
    BigDecimal fee;

    @NotNull
    Currency currency;
}
