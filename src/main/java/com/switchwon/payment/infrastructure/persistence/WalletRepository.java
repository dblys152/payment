package com.switchwon.payment.infrastructure.persistence;

import com.switchwon.payment.domain.wallet.Wallet;
import com.switchwon.payment.domain.wallet.WalletId;
import com.switchwon.payment.refs.user.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, WalletId> {
    Optional<Wallet> findByUserId(UserId userId);
}
