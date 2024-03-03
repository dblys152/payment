package com.switchwon.payment.domain.core.command.processor;

import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.core.command.handler.PaymentHandler;
import com.switchwon.payment.domain.core.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PgApproveResult implements PaymentResult {
    public static final String SUCCEDED_RESULT = "A0000";

    private String type;
    private String result;
    private String message;
    private String approvalNumber;
    private LocalDateTime timestamp;
    private BigDecimal amount;
    private BigDecimal fees;
    private Currency currency;

    @Override
    public boolean isSucceded() {
        return this.result.equals(SUCCEDED_RESULT);
    }

    @Override
    public Payment handle(PaymentHandler handler, ApprovePaymentCommand command) {
        return handler.handle(this, command);
    }
}
