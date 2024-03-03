package com.switchwon.payment.domain.core.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PaymentMethod {
    CREDIT_CARD("creditCard");

    final String value;

    PaymentMethod(final String value) {
        this.value = value;
    }

    @JsonCreator
    public static PaymentMethod fromValue(String value) {
        return Arrays.stream(PaymentMethod.values())
                .filter(p -> p.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such PaymentMethod with value: " + value));
    }

    public boolean isCreditCard() {
        return CREDIT_CARD.equals(this);
    }
}
