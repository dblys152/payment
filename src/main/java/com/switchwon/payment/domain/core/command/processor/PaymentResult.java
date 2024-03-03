package com.switchwon.payment.domain.core.command.processor;

import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.core.command.handler.PaymentHandler;
import com.switchwon.payment.domain.core.entity.Payment;

public interface PaymentResult {
    boolean isSucceded();
    Payment handle(PaymentHandler handler, ApprovePaymentCommand command);
}
