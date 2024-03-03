package com.switchwon.payment.domain.wallet;

import com.switchwon.payment.domain.Currency;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value(staticConstructor = "of")
public class Balance {
    @NotNull
    private Currency currency;

    @Valid
    @NotNull
    private BigDecimal amount;

    @NotNull
    private LocalDateTime lastTimestamp;

    public static Balance init(Currency currency) {
        return Balance.of(currency, BigDecimal.ZERO, LocalDateTime.now());
    }

    public Balance use(BigDecimal usedAmount) {
        if (this.amount.compareTo(usedAmount) < 0) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        return new Balance(
                this.currency, this.amount.subtract(usedAmount), LocalDateTime.now());
    }
}
