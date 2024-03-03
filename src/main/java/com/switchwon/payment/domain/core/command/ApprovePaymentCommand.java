package com.switchwon.payment.domain.core.command;

import com.switchwon.payment.common.data.SelfValidating;
import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.core.entity.PaymentDetails;
import com.switchwon.payment.domain.core.entity.PaymentMethod;
import com.switchwon.payment.domain.merchant.Merchant;
import com.switchwon.payment.refs.user.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ApprovePaymentCommand extends SelfValidating<ApprovePaymentCommand> {
    @NotNull
    private User user;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Currency currency;

    @NotNull
    private Merchant merchant;

    @NotNull
    private String name;

    @NotNull
    private PaymentMethod method;

    @NotNull
    private PaymentDetails details;

    public ApprovePaymentCommand(User user, BigDecimal amount, Currency currency, Merchant merchant, PaymentMethod method, PaymentDetails details) {
        this.user = user;
        this.amount = amount;
        this.currency = currency;
        this.merchant = merchant;
        this.name = "product_name";
        this.method = method;
        this.details = details;
        validateSelf();
    }
}
