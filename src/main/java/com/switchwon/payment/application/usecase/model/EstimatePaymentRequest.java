package com.switchwon.payment.application.usecase.model;

import com.switchwon.payment.domain.Currency;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EstimatePaymentRequest {
    @NotNull
    private BigDecimal amount;

    @NotNull
    private Currency currency;

    @NotNull
    @Size(min = 1, max = 20)
    private String destination;

    @NotNull
    @Size(min = 1, max = 20)
    private String userId;
}
