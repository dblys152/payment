package com.switchwon.payment.domain.core.entity;

import com.switchwon.payment.common.data.StringId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value(staticConstructor = "of")
public class PaymentId implements StringId {
    @NotNull
    @Size(min = 1, max = 20)
    String id;

    @Override
    public String get() {
        return this.id;
    }
}
