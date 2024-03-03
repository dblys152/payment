package com.switchwon.payment.application.usecase;

import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.core.entity.Payment;

public interface ApprovalPaymentUseCase {
    Payment approve(ApprovePaymentCommand command);
}
