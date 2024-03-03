package com.switchwon.payment.domain.wallet;

import com.switchwon.payment.domain.wallet.Wallet;

public interface RecordWalletPort {
    Wallet save(Wallet wallet);
}
