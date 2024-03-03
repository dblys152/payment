package com.switchwon.payment.refs.user.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class User {
    @NotNull
    UserId userId;

    @NotNull
    Profile profile;
}
