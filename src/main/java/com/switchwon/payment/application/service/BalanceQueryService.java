package com.switchwon.payment.application.service;

import com.switchwon.payment.application.usecase.GetBalanceQuery;
import com.switchwon.payment.domain.wallet.LoadWalletPort;
import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.wallet.Balance;
import com.switchwon.payment.domain.wallet.Wallet;
import com.switchwon.payment.refs.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BalanceQueryService implements GetBalanceQuery {
    private final LoadWalletPort loadWalletPort;

    @Override
    public Balance getBalance(UserId userId, Currency currency) {
        Wallet wallet = loadWalletPort.findById(userId);

        return wallet.getBalanceByCurrency(currency);
    }
}
