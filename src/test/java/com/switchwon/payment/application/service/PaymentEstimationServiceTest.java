package com.switchwon.payment.application.service;

import com.switchwon.payment.domain.estimation.EstimatePaymentCommand;
import com.switchwon.payment.domain.estimation.PaymentEstimation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentEstimationServiceTest {
    @InjectMocks
    private PaymentEstimationService sut;

    @Test
    void 결제_금액을_예상한다() {
        EstimatePaymentCommand command = mock(EstimatePaymentCommand.class);

        when(command.execute()).thenReturn(mock(PaymentEstimation.class));
        PaymentEstimation actual = sut.estimate(command);

        assertThat(actual).isNotNull();
    }
}