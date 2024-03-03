package com.switchwon.payment.infrastructure;

import com.switchwon.payment.common.exception.ExceptionMessages;
import com.switchwon.payment.domain.wallet.LoadWalletPort;
import com.switchwon.payment.domain.wallet.RecordWalletPort;
import com.switchwon.payment.domain.wallet.Wallet;
import com.switchwon.payment.infrastructure.persistence.WalletRepository;
import com.switchwon.payment.refs.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class WalletPersistenceAdapter implements RecordWalletPort, LoadWalletPort {
    private final WalletRepository repository;

    @Override
    public Wallet save(Wallet wallet) {
        return repository.save(wallet);
    }


    @Override
    public Wallet findById(UserId userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException(ExceptionMessages.NO_DATA_MESSAGE));
    }
}
