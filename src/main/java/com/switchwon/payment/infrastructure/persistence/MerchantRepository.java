package com.switchwon.payment.infrastructure.persistence;

import com.switchwon.payment.domain.merchant.Merchant;
import com.switchwon.payment.domain.merchant.MerchantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, MerchantId> {

}
