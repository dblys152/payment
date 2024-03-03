package com.switchwon.payment.domain.core.command.handler;

import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.core.command.processor.PgApproveResult;
import com.switchwon.payment.domain.core.entity.Payment;

public interface PaymentHandler {
    Payment handle(PgApproveResult result, ApprovePaymentCommand command);
}
