package com.switchwon.payment.infrastructure.persistence;

import com.switchwon.payment.domain.wallet.Wallet;
import com.switchwon.payment.refs.user.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, UserId> {

}
