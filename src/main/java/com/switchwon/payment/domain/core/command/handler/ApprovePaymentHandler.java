package com.switchwon.payment.domain.core.command.handler;

import com.switchwon.payment.domain.core.entity.RecordPaymentPort;
import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.core.command.processor.PgApproveResult;
import com.switchwon.payment.domain.core.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprovePaymentHandler implements PaymentHandler {
    private final RecordPaymentPort recordPaymentPort;

    @Override
    public Payment handle(PgApproveResult result, ApprovePaymentCommand command) {
        PaymentStatus status = result.isSucceded() ? PaymentStatus.APPROVED : PaymentStatus.FAILED;

        Payment payment = Payment.create(
                command.getUser().getUserId(),
                status,
                result.getTimestamp(),
                result.getApprovalNumber(),
                command.getMerchant(),
                command.getMethod(),
                command.getCurrency(),
                command.getAmount(),
                Fees.of(List.of(
                        Fee.of(FeeType.BASE, result.getFees(), result.getCurrency()))),
                command.getDetails()
        );

        return recordPaymentPort.save(payment);
    }
}
