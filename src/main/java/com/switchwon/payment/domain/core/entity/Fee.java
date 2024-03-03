package com.switchwon.payment.domain.core.entity;

import com.switchwon.payment.domain.Currency;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Fee {
    @NotNull
    private FeeType type;

    @NotNull
    private BigDecimal fee;

    @NotNull
    private Currency currency;

    public static Fee of(FeeType type, BigDecimal fee, Currency currency) {
        return new Fee(type, fee, currency);
    }
}
