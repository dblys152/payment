package com.switchwon.payment.domain.merchant;

import com.switchwon.payment.domain.Currency;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FixedAmountFees {
    @Valid
    @NotNull
    private List<FixedAmountFee> items;

    public static FixedAmountFees of(List<FixedAmountFee> items) {
        return new FixedAmountFees(items);
    }

    public FixedAmountFee getByCurrency(Currency currency) {
        return this.items.stream()
                .filter(fixedAmountFee -> fixedAmountFee.getCurrency().equals(currency))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
