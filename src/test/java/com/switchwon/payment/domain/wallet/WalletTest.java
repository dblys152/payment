package com.switchwon.payment.domain.wallet;

import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.fixture.SupportWalletFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class WalletTest extends SupportWalletFixture {
    private Wallet wallet;

    @BeforeEach
    void setUp() {
        wallet = Wallet.of(USER_ID, BALANCES, NOW, NOW);
    }

    @Test
    void 잔액을_조회한다() {
        Balance actual = wallet.getBalanceByCurrency(Currency.USD);

        assertThat(actual).isNotNull();
        assertThat(actual.getCurrency()).isEqualTo(Currency.USD);
    }

    @Test
    void 잔액을_사용한다() {
        Balance usedBalance = Balance.of(Currency.USD, BigDecimal.valueOf(155), NOW);
        Balance originBalance = wallet.getBalances().getByCurrency(Currency.USD);

        wallet.useBalance(usedBalance);
        BigDecimal currentBalanceAmount = wallet.getBalanceByCurrency(Currency.USD).getAmount();

        assertThat(currentBalanceAmount).isEqualTo(
                originBalance.getAmount().subtract(usedBalance.getAmount()));
    }
}