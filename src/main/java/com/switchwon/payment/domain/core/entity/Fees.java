package com.switchwon.payment.domain.core.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class Fees {
    @Valid
    @NotNull
    List<Fee> items;
}
