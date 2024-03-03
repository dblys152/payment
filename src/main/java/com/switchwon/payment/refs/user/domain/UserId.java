package com.switchwon.payment.refs.user.domain;

import com.switchwon.payment.common.data.StringId;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserId implements StringId {
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USER_ID", nullable = false)
    private String id;

    public static UserId of(String id) {
        return new UserId(id);
    }

    @Override
    public String get() {
        return this.id;
    }
}
