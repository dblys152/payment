package com.switchwon.payment.domain.core.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PaymentDetails {
    @NotNull
    @Size(min = 19, max = 19)
    private String cardNumber;

    @NotNull
    @Size(min = 5, max = 5)
    private String expiryDate;

    @NotNull
    @Size(min = 3, max = 3)
    private String cvv;

    public static PaymentDetails of(String cardNumber, String expiryDate, String cvv) {
        return new PaymentDetails(cardNumber, expiryDate, cvv);
    }
}
