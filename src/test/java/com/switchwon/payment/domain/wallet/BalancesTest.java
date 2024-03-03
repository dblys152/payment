package com.switchwon.payment.domain.wallet;

import com.switchwon.payment.domain.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BalancesTest {
    private static final LocalDateTime TIMESTAMP = LocalDateTime.now();

    private Balances balances;

    @BeforeEach
    void setUp() {
        balances = Balances.of(List.of(
                Balance.of(Currency.USD, BigDecimal.valueOf(2000), TIMESTAMP)));
    }

    @Test
    void 잔액을_조회한다() {
        Balance actual = balances.getByCurrency(Currency.USD);

        assertThat(actual).isNotNull();
        assertThat(actual.getCurrency()).isEqualTo(Currency.USD);
    }

    @Test
    void 찾는_통화가_없으면_0원으로_반환한다() {
        Balance actual = balances.getByCurrency(Currency.KRW);

        assertThat(actual).isNotNull();
        assertThat(actual.getCurrency()).isEqualTo(Currency.KRW);
        assertThat(actual.getAmount()).isEqualTo(BigDecimal.ZERO);
    }
}