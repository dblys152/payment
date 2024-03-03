package com.switchwon.payment.domain.core.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Fees {
    @Valid
    @NotNull
    private List<Fee> items;

    public static Fees of(List<Fee> items) {
        return new Fees(items);
    }
}
