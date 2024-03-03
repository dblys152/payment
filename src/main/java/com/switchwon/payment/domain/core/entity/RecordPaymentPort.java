package com.switchwon.payment.domain.core.entity;

import com.switchwon.payment.domain.core.entity.Payment;

public interface RecordPaymentPort {
    Payment save(Payment payment);
}
