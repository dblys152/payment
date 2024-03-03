package com.switchwon.payment.infrastructure;

import com.switchwon.payment.common.exception.ExceptionMessages;
import com.switchwon.payment.domain.merchant.LoadMerchantPort;
import com.switchwon.payment.domain.merchant.Merchant;
import com.switchwon.payment.domain.merchant.MerchantId;
import com.switchwon.payment.infrastructure.persistence.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class MerchantPersistenceAdapter implements LoadMerchantPort {
    private final MerchantRepository repository;

    @Override
    public Merchant findById(MerchantId merchantId) {
        return repository.findById(merchantId)
                .orElseThrow(() -> new NoSuchElementException(ExceptionMessages.NO_DATA_MESSAGE));
    }
}
