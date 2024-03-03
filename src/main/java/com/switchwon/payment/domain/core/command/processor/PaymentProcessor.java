package com.switchwon.payment.domain.core.command.processor;

import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.core.entity.PaymentMethod;

public abstract class PaymentProcessor implements SimpleProcessor<ApprovePaymentCommand, PaymentResult> {
    private final PaymentMethod method;

    public PaymentProcessor(PaymentMethod method) {
        this.method = method;
    }

    @Override
    public boolean support(ApprovePaymentCommand factor) {
        return method.equals(factor.getMethod());
    }

    @Override
    public PaymentResult execute(ApprovePaymentCommand factor) {
        return _execute(factor);
    }

    protected abstract PaymentResult _execute(ApprovePaymentCommand factor);
}
