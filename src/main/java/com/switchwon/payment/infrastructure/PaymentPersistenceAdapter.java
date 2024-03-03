package com.switchwon.payment.infrastructure;

import com.switchwon.payment.domain.core.entity.RecordPaymentPort;
import com.switchwon.payment.domain.core.entity.Payment;
import com.switchwon.payment.infrastructure.persistence.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements RecordPaymentPort {
    private final PaymentRepository repository;

    @Override
    public Payment save(Payment payment) {
        return repository.save(payment);
    }
}
