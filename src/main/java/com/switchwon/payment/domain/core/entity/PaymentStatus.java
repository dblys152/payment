package com.switchwon.payment.domain.core.entity;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    APPROVED("approved"),
    FAILED("failed");

    final String value;

    PaymentStatus(final String value) {
        this.value = value;
    }
}
