package com.switchwon.payment.domain.merchant;

import com.switchwon.payment.domain.merchant.Merchant;
import com.switchwon.payment.domain.merchant.MerchantId;

public interface LoadMerchantPort {
    Merchant findById(MerchantId merchantId);
}
