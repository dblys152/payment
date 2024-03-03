package com.switchwon.payment.domain.wallet;

import com.switchwon.payment.domain.Currency;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.EnumSet;
import java.util.List;

@Value(staticConstructor = "of")
public class Balances {
    @Valid
    @NotNull
    List<Balance> items;

    public Balances(List<Balance> items) {
        this.items = items;
        validateCurrencyRedundancy();
    }

    private void validateCurrencyRedundancy() {
        EnumSet<Currency> currencySet = EnumSet.noneOf(Currency.class);
        this.items.stream()
                .filter(b -> !currencySet.add(b.getCurrency()))
                .findFirst()
                .ifPresent(t -> {
                    throw new IllegalArgumentException("동일한 통화를 중복으로 등록할 수 없습니다.");
                });
    }

    protected Balance getByCurrency(Currency currency) {
        return this.items.stream()
                .filter(b -> b.getCurrency().equals(currency))
                .findFirst()
                .orElse(Balance.init(currency));
    }

    protected Balances use(Balance usedBalance) {
        return new Balances(this.items.stream()
                .map(b -> {
                    if (b.getCurrency().equals(usedBalance.getCurrency())) {
                        return b.use(usedBalance.getAmount());
                    }
                    return b;
                })
                .toList());
    }
}
