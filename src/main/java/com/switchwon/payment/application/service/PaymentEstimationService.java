package com.switchwon.payment.application.service;

import com.switchwon.payment.application.usecase.EstimatePaymentUseCase;
import com.switchwon.payment.domain.estimation.EstimatePaymentCommand;
import com.switchwon.payment.domain.estimation.PaymentEstimation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentEstimationService implements EstimatePaymentUseCase {
    @Override
    public PaymentEstimation estimate(EstimatePaymentCommand command) {
        return command.execute();
    }
}
