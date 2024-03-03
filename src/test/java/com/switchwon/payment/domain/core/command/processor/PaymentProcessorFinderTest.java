package com.switchwon.payment.domain.core.command.processor;

import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.core.entity.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class PaymentProcessorFinderTest {
    private PaymentProcessorFinder<ApprovePaymentCommand, PaymentResult> paymentProcessorFinder;

    @Mock
    private RequestPgApiPort requestPgApiPort;

    @BeforeEach
    void setUp() {
        paymentProcessorFinder = new PaymentProcessorFinder<>(List.of(
                new CreditCardPaymentProcessor(requestPgApiPort)));
    }

    @Test
    void 결제_프로세서를_가져온다() {
        ApprovePaymentCommand command = mock(ApprovePaymentCommand.class);
        given(command.getMethod()).willReturn(PaymentMethod.CREDIT_CARD);

        SimpleProcessor<ApprovePaymentCommand, PaymentResult> actual = paymentProcessorFinder.getProcessor(command);

        assertThat(actual).isNotNull();
        assertThat(actual).isInstanceOf(CreditCardPaymentProcessor.class);
    }
}