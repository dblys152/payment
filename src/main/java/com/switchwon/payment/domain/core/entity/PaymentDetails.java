package com.switchwon.payment.domain.core.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value(staticConstructor = "of")
public class PaymentDetails {
    @NotNull
    @Size(min = 19, max = 19)
    String cardNumber;

    @NotNull
    @Size(min = 5, max = 5)
    String expiryDate;

    @NotNull
    @Size(min = 3, max = 3)
    String cvv;;
}
