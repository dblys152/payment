package com.switchwon.payment.fixture;

import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.merchant.FixedAmountFee;
import com.switchwon.payment.domain.merchant.FixedAmountFees;
import com.switchwon.payment.domain.merchant.MerchantId;

import java.math.BigDecimal;
import java.util.List;

public class SupportMerchantFixture {
    protected static final MerchantId MERCHANT_ID = MerchantId.of("9999999999");
    protected static final FixedAmountFee USD_FIXED_AMOUNT_FEE = FixedAmountFee.of(BigDecimal.valueOf(5), Currency.USD);
    protected static final FixedAmountFees FIXED_AMOUNT_FEES = FixedAmountFees.of(List.of(USD_FIXED_AMOUNT_FEE));
}
