package com.switchwon.payment.application.service;

import com.switchwon.payment.application.usecase.ApprovalPaymentUseCase;
import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.core.command.handler.PaymentHandler;
import com.switchwon.payment.domain.core.command.processor.PaymentProcessorFinder;
import com.switchwon.payment.domain.core.command.processor.PaymentResult;
import com.switchwon.payment.domain.core.command.processor.SimpleProcessor;
import com.switchwon.payment.domain.core.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class PaymentCommandService implements ApprovalPaymentUseCase {
    private final PaymentProcessorFinder<ApprovePaymentCommand, PaymentResult> paymentProcessorFinder;
    private final PaymentHandler paymentHandler;

    @Override
    public Payment approve(ApprovePaymentCommand command) {
        SimpleProcessor<ApprovePaymentCommand, PaymentResult> processor = paymentProcessorFinder.getProcessor(command);

        PaymentResult result = processor.execute(command);

        Payment payment = result.handle(paymentHandler, command);

        // TODO: 만약 결제 수단이 계좌와 연결된 카드라면 잔액 사용 처리 (payment 이벤트 발행 -> balance 사용)

        return payment;
    }
}
