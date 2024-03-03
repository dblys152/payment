package com.switchwon.payment.fixture;

import com.switchwon.payment.domain.core.entity.Fees;
import com.switchwon.payment.domain.core.entity.PaymentDetails;
import com.switchwon.payment.domain.core.entity.PaymentId;
import com.switchwon.payment.domain.merchant.MerchantId;
import com.switchwon.payment.refs.user.domain.UserId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SupportPaymentFixture {
    protected static final LocalDateTime TIMESTAMP =LocalDateTime.now();
    protected static final PaymentId PAYMENT_ID = PaymentId.of("9999999999");
    protected static final UserId USER_ID = UserId.of("8888888");
    protected static final String APPROVAL_NUMBER = "2024010100004";
    protected static final MerchantId MERCHANT_ID = MerchantId.of("7777777");
    protected static final BigDecimal USD_200_DOLLARS = BigDecimal.valueOf(200);
    protected static final Fees FEES = Fees.of(List.of());
    protected static final String CARD_NUMBER = "5426-1324-2324-4365";
    protected static final String EXPIRY_DATE = "12/24";
    protected static final String CVV = "123";
    protected static final PaymentDetails PAYMENT_DETAILS = PaymentDetails.of(CARD_NUMBER, EXPIRY_DATE, CVV);
}
