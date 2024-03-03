package com.switchwon.payment.domain.core.entity;

import com.switchwon.payment.common.data.StringId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PaymentId implements StringId {
    @NotNull
    @Size(min = 1, max = 20)
    private String id;

    public static PaymentId of(String id) {
        return new PaymentId(id);
    }

    @Override
    public String get() {
        return this.id;
    }
}
