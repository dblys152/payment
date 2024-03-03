package com.switchwon.payment.domain.merchant;

import com.switchwon.payment.common.data.StringId;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MerchantId implements StringId {
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MERCHANT_ID", nullable = false)
    private String id;

    public static MerchantId of(String id) {
        return new MerchantId(id);
    }

    @Override
    public String get() {
        return this.id;
    }
}
