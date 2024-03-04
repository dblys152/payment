package com.switchwon.payment.application.usecase.model;

import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.core.entity.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
public class ApprovePaymentRequest {
    @Valid
    @NotNull
    @Size(min = 1, max = 20)
    private String userId;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Currency currency;

    @NotNull
    @Size(min = 1, max = 20)
    private String merchantId;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private PaymentDetailsRequest paymentDetails;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentDetailsRequest {
        @NotNull
        @Size(min = 19, max = 19)
        String cardNumber;

        @NotNull
        @Size(min = 5, max = 5)
        String expiryDate;

        @NotNull
        @Size(min = 3, max = 3)
        String cvv;
    }
}
