package com.switchwon.payment.domain.merchant;

import com.switchwon.payment.domain.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FixedAmountFeesTest {
    private FixedAmountFees fixedAmountFees;

    @BeforeEach
    void setUp() {
        fixedAmountFees = FixedAmountFees.of(List.of(
                FixedAmountFee.of(BigDecimal.valueOf(5), Currency.USD)));
    }

    @Test
    void 고정_금액_수수료를_조회한다() {
        FixedAmountFee actual = fixedAmountFees.getByCurrency(Currency.USD);

        assertThat(actual).isNotNull();
    }

    @Test
    void 고정_금액_수수료_찾지_못하면_에러를_반환한다() {
        assertThatThrownBy(() -> fixedAmountFees.getByCurrency(Currency.KRW)).isInstanceOf(IllegalArgumentException.class);
    }
}