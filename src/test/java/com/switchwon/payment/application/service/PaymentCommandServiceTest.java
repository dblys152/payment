package com.switchwon.payment.application.service;

import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.core.command.handler.PaymentHandler;
import com.switchwon.payment.domain.core.command.processor.PaymentProcessorFinder;
import com.switchwon.payment.domain.core.command.processor.PaymentResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentCommandServiceTest {
    @InjectMocks
    private PaymentCommandService sut;

    @Mock
    private PaymentProcessorFinder<ApprovePaymentCommand, PaymentResult> paymentProcessorFinder;
    @Mock
    private PaymentHandler paymentHandler;

    @Test
    void 결제_승인한다() {

    }
}