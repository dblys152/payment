package com.switchwon.payment.domain.core.entity;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    CREDIT_CARD("creditCard");

    final String value;

    PaymentMethod(final String value) {
        this.value = value;
    }

    public boolean isCreditCard() {
        return CREDIT_CARD.equals(this);
    }
}
