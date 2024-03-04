package com.switchwon.payment.presentation.model;

import com.switchwon.payment.domain.core.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentModel {
    String paymentId;
    String status;
    BigDecimal amount;
    String currency;
    LocalDateTime timestamp;

    public static PaymentModel fromDomain(Payment payment) {
        return new PaymentModel(
                payment.getPaymentId().get(),
                payment.getStatus().getValue(),
                payment.getAmount(),
                payment.getCurrency().name(),
                payment.getRegisteredTimestamp()
        );
    }
}
