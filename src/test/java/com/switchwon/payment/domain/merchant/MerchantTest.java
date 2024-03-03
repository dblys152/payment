package com.switchwon.payment.domain.merchant;

import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.fixture.SupportMerchantFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MerchantTest extends SupportMerchantFixture {
    private Merchant merchant;

    @BeforeEach
    void setUp() {
        merchant = Merchant.of(MERCHANT_ID, FIXED_AMOUNT_FEES);
    }

    @Test
    void 고정_금액_수수료를_조회한다() {
        FixedAmountFee actual = merchant.getFixedAmountFeeByCurrency(Currency.USD);

        assertThat(actual).isNotNull();
    }
}