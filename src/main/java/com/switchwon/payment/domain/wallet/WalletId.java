package com.switchwon.payment.domain.wallet;

import com.switchwon.payment.common.data.StringId;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WalletId implements StringId {
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "WALLET_ID", nullable = false)
    private String id;

    public static WalletId of(String id) {
        return new WalletId(id);
    }

    @Override
    public String get() {
        return this.id;
    }
}
