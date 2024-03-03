package com.switchwon.payment.refs.user.domain;

import com.switchwon.payment.common.data.StringId;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class UserId implements StringId {
    @NotNull
    String id;

    @Override
    public String get() {
        return this.id;
    }
}
