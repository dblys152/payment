package com.switchwon.payment.domain.wallet;

import com.switchwon.payment.refs.user.domain.UserId;

public interface LoadWalletPort {
    Wallet findByUserId(UserId userId);
}
