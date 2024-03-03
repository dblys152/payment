package com.switchwon.payment.application.usecase;

import com.switchwon.payment.domain.estimation.EstimatePaymentCommand;
import com.switchwon.payment.domain.estimation.PaymentEstimation;

public interface EstimatePaymentUseCase {
    PaymentEstimation estimate(EstimatePaymentCommand command);
}
