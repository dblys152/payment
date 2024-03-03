package com.switchwon.payment.domain.merchant;

import com.switchwon.payment.domain.Currency;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class FixedAmountFees {
    @Valid
    @NotNull
    List<FixedAmountFee> items;

    public FixedAmountFee getByCurrency(Currency currency) {
        return this.items.stream()
                .filter(fixedAmountFee -> fixedAmountFee.getCurrency().equals(currency))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
