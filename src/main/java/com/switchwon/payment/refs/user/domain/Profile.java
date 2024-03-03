package com.switchwon.payment.refs.user.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class Profile {
    @NotNull
    String name;

    @NotNull
    String email;

    @NotNull
    String tel;
}
