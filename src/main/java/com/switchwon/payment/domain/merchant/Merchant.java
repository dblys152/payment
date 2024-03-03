package com.switchwon.payment.domain.merchant;

import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.merchant.converter.FixedAmountFeesConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "MERCHANTS")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Merchant {
    @EmbeddedId
    @NotNull
    private MerchantId merchantId;

    @NotNull
    @Column(name = "FIXED_AMOUNT_FEES", nullable = false)
    @Convert(converter = FixedAmountFeesConverter.class)
    private FixedAmountFees fixedAmountFees;

    public static Merchant of(MerchantId merchantId, FixedAmountFees fixedAmountFees) {
        return new Merchant(merchantId, fixedAmountFees);
    }

    public FixedAmountFee getFixedAmountFeeByCurrency(Currency currency) {
        return this.fixedAmountFees.getByCurrency(currency);
    }
}
