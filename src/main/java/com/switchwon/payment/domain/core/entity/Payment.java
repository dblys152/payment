package com.switchwon.payment.domain.core.entity;

import com.github.f4b6a3.tsid.TsidCreator;
import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.core.entity.converter.DetailsConverter;
import com.switchwon.payment.domain.core.entity.converter.FeesConverter;
import com.switchwon.payment.domain.core.entity.converter.PaymentMethodConverter;
import com.switchwon.payment.domain.core.entity.converter.PaymentStatusConverter;
import com.switchwon.payment.domain.merchant.MerchantId;
import com.switchwon.payment.refs.user.domain.UserId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PAYMENTS")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Payment extends AbstractAggregateRoot<Payment> {
    @EmbeddedId
    @NotNull
    @Column(name = "PAYMENT_ID", nullable = false)
    private PaymentId paymentId;

    @NotNull
    @Column(name = "USER_ID", nullable = false)
    private UserId userId;

    @NotNull
    @Column(name = "STATUS", nullable = false)
    @Convert(converter = PaymentStatusConverter.class)
    private PaymentStatus status;

    @NotNull
    @Column(name = "PAYMENT_TIMESTAMP", nullable = false)
    private LocalDateTime paymentTimestamp;

    @NotNull
    @Column(name = "APPROVAL_NUMBER", nullable = false)
    private String approvalNumber;

    @NotNull
    @Column(name = "MERCHANT_ID", nullable = false)
    private MerchantId merchantId;

    @NotNull
    @Column(name = "METHOD", nullable = false)
    @Convert(converter = PaymentMethodConverter.class)
    private PaymentMethod method;

    @NotNull
    @Column(name = "CURRENCY", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @NotNull
    @Column(name = "Fees", nullable = false)
    @Convert(converter = FeesConverter.class)
    private Fees fees;

    @NotNull
    @Column(name = "DETAILS", nullable = false)
    @Convert(converter = DetailsConverter.class)
    private PaymentDetails details;

    @NotNull
    @Column(name = "REGISTERED_TIMESTAMP", nullable = false)
    private LocalDateTime registeredTimestamp;

    public static Payment of(
            PaymentId paymentId,
            UserId userId,
            PaymentStatus status,
            LocalDateTime paymentTimestamp,
            String approvalNumber,
            MerchantId merchantId,
            PaymentMethod method,
            Currency currency,
            BigDecimal amount,
            Fees fees,
            PaymentDetails details,
            LocalDateTime registeredTimestamp
    ) {
        return new Payment(
                paymentId, userId, status, paymentTimestamp, approvalNumber, merchantId, method, currency, amount, fees, details, registeredTimestamp);
    }

    public static Payment create(
            UserId userId,
            PaymentStatus status,
            LocalDateTime paymentTimestamp,
            String approvalNumber,
            MerchantId merchantId,
            PaymentMethod method,
            Currency currency,
            BigDecimal amount,
            Fees fees,
            PaymentDetails details
    ) {
        PaymentId paymentId = PaymentId.of(String.valueOf(TsidCreator.getTsid256().toLong()));
        return new Payment(
                paymentId,
                userId,
                status,
                paymentTimestamp,
                approvalNumber,
                merchantId,
                method,
                currency,
                amount,
                fees,
                details,
                LocalDateTime.now()
        );
    }
}
