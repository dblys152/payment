package com.switchwon.payment.domain.merchant;

import com.switchwon.payment.domain.Currency;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FixedAmountFee {
    @NotNull
    private BigDecimal amount;

    @NotNull
    private Currency currency;

    public static FixedAmountFee of(BigDecimal amount, Currency currency) {
        return new FixedAmountFee(amount, currency);
    }
}
