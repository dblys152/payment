package com.switchwon.payment.domain.wallet;

import com.switchwon.payment.domain.Currency;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Balance {
    @NotNull
    private Currency currency;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private LocalDateTime lastTimestamp;

    public static Balance of(Currency currency, BigDecimal amount, LocalDateTime lastTimestamp) {
        return new Balance(currency, amount, lastTimestamp);
    }

    public static Balance init(Currency currency) {
        return new Balance(currency, BigDecimal.ZERO, LocalDateTime.now());
    }

    public Balance use(BigDecimal usedAmount) {
        if (this.amount.compareTo(usedAmount) < 0) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        return new Balance(
                this.currency, this.amount.subtract(usedAmount), LocalDateTime.now());
    }
}
