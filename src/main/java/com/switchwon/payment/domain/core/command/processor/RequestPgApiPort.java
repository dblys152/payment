package com.switchwon.payment.domain.core.command.processor;

public interface RequestPgApiPort {
    PaymentResult approve(PgApproveRequest request);
}
