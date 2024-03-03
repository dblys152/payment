package com.switchwon.payment.application.usecase;

import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.wallet.Balance;
import com.switchwon.payment.refs.user.domain.UserId;

public interface GetBalanceQuery {
    Balance getBalance(UserId userId, Currency currency);
}
