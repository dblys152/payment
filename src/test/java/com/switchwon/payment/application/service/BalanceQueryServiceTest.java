package com.switchwon.payment.application.service;

import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.wallet.Balance;
import com.switchwon.payment.domain.wallet.LoadWalletPort;
import com.switchwon.payment.domain.wallet.Wallet;
import com.switchwon.payment.refs.user.domain.UserId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BalanceQueryServiceTest {
    private static final UserId USER_ID = UserId.of("99999");

    @InjectMocks
    private BalanceQueryService sut;

    @Mock
    private LoadWalletPort loadWalletPort;

    @Test
    void 잔액을_조회한다() {
        Wallet wallet = mock(Wallet.class);
        given(loadWalletPort.findById(USER_ID)).willReturn(wallet);

        when(wallet.getBalanceByCurrency(Currency.USD)).thenReturn(mock(Balance.class));
        Balance actual = sut.getBalance(USER_ID, Currency.USD);

        assertThat(actual).isNotNull();
        then(loadWalletPort).should().findById(USER_ID);
    }
}