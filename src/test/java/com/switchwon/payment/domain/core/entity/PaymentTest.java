package com.switchwon.payment.domain.core.entity;

import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.fixture.SupportPaymentFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest extends SupportPaymentFixture {
    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = Payment.of(
                PAYMENT_ID, USER_ID, PaymentStatus.APPROVED, TIMESTAMP, APPROVAL_NUMBER, MERCHANT_ID, PaymentMethod.CREDIT_CARD, Currency.USD, USD_200_DOLLARS, FEES, PAYMENT_DETAILS, TIMESTAMP);
    }

    @Test
    void 결제를_생성한다() {
        Payment actual = Payment.create(
                USER_ID, PaymentStatus.APPROVED, TIMESTAMP, APPROVAL_NUMBER, MERCHANT_ID, PaymentMethod.CREDIT_CARD, Currency.USD, USD_200_DOLLARS, FEES, PAYMENT_DETAILS);

        assertThat(actual).isNotNull();
        assertThat(actual.getPaymentId()).isNotNull();
        assertThat(actual.getRegisteredTimestamp()).isNotNull();
    }
}