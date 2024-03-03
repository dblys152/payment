package com.switchwon.payment.domain.estimation;

import com.switchwon.payment.common.data.SelfValidating;
import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.merchant.FixedAmountFee;
import com.switchwon.payment.domain.merchant.Merchant;
import com.switchwon.payment.refs.user.domain.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class EstimatePaymentCommand extends SelfValidating<EstimatePaymentCommand> {
    @Valid
    @NotNull
    private BigDecimal amount;

    @NotNull
    private Currency currency;

    @NotNull
    private Merchant merchant;

    @NotNull
    private User user;

    public EstimatePaymentCommand(BigDecimal amount, Currency currency, Merchant merchant, User user) {
        this.amount = amount;
        this.currency = currency;
        this.merchant = merchant;
        this.user = user;
        validateSelf();
    }

    public PaymentEstimation execute() {
        // TODO: 사용자별 수수료 차등 계산

        FixedAmountFee fixedAmountFee = this.merchant.getFixedAmountFeeByCurrency(this.currency);
        BigDecimal estimatedTotal = this.amount.add(fixedAmountFee.getAmount());

        return PaymentEstimation.create(
                this.amount, this.currency, estimatedTotal, fixedAmountFee.getAmount());
    }
}
