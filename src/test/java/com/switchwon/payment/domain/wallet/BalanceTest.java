package com.switchwon.payment.domain.wallet;

import com.switchwon.payment.domain.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BalanceTest {
    private static final LocalDateTime TIMESTAMP = LocalDateTime.now();

    private Balance balance;

    @BeforeEach
    void setUp() {
        balance = Balance.of(Currency.USD, BigDecimal.valueOf(2000), TIMESTAMP);
    }

    @Test
    void 잔액을_사용한다() {
        BigDecimal originAmount = balance.getAmount();

        Balance actual = balance.use(BigDecimal.valueOf(155));

        assertThat(actual.getAmount()).isEqualTo(originAmount.subtract(BigDecimal.valueOf(155)));
        assertThat(actual.getLastTimestamp()).isAfter(TIMESTAMP);
    }

    @Test
    void 잔액_사용_시_잔액이_부족하면_에러를_반환한다() {
        assertThatThrownBy(() -> balance.use(BigDecimal.valueOf(2001))).isInstanceOf(IllegalArgumentException.class);
    }
}