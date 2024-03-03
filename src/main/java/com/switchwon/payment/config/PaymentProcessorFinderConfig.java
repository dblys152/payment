package com.switchwon.payment.config;

import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.core.command.processor.CreditCardPaymentProcessor;
import com.switchwon.payment.domain.core.command.processor.PaymentProcessorFinder;
import com.switchwon.payment.domain.core.command.processor.PaymentResult;
import com.switchwon.payment.domain.core.command.processor.RequestPgApiPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PaymentProcessorFinderConfig {
    private final RequestPgApiPort requestPgApiPort;

    @Bean
    public PaymentProcessorFinder<ApprovePaymentCommand, PaymentResult> paymentProcessorFinder() {
        return new PaymentProcessorFinder<>(List.of(
                new CreditCardPaymentProcessor(requestPgApiPort)));
    }
}
