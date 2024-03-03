package com.switchwon.payment.infrastructure;

import com.switchwon.payment.domain.core.command.processor.PaymentResult;
import com.switchwon.payment.domain.core.command.processor.PgApproveRequest;
import com.switchwon.payment.domain.core.command.processor.PgApproveResult;
import com.switchwon.payment.domain.core.command.processor.RequestPgApiPort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class MockPgApiAdapter implements RequestPgApiPort {
    @Override
    public PaymentResult approve(PgApproveRequest request) {
        LocalDateTime timestamp = LocalDateTime.now();
        return new PgApproveResult(
                "payment",
                PgApproveResult.SUCCEDED_RESULT,
                "approval",
                "2024010100004",
                timestamp,
                request.getAmount(),
                BigDecimal.valueOf(5),
                request.getCurrency()
        );
    }
}
