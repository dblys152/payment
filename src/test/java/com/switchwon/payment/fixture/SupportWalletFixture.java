package com.switchwon.payment.fixture;

import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.wallet.Balance;
import com.switchwon.payment.domain.wallet.Balances;
import com.switchwon.payment.refs.user.domain.UserId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SupportWalletFixture {
    protected static final LocalDateTime TIMESTAMP = LocalDateTime.now();
    protected static final UserId USER_ID = UserId.of("99999999999");
    protected static final Balance USD_BALANCE = Balance.of(Currency.USD, BigDecimal.valueOf(2000), TIMESTAMP);
    protected static final Balances BALANCES = Balances.of(List.of(USD_BALANCE));
}
