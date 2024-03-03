package com.switchwon.payment.domain.wallet;

import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.wallet.converter.BalancesConverter;
import com.switchwon.payment.refs.user.domain.UserId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "WALLETS")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Wallet extends AbstractAggregateRoot<Wallet> {
    @EmbeddedId
    @NotNull
    @Column(name = "USER_ID", nullable = false)
    private UserId userId;

    @NotNull
    @Column(name = "BALANCES", nullable = false)
    @Convert(converter = BalancesConverter.class)
    private Balances balances;

    @NotNull
    @Column(name = "REGISTERED_TIMESTAMP", nullable = false)
    private LocalDateTime registeredTimestamp;

    @NotNull
    @Column(name = "LAST_TIMESTAMP", nullable = false)
    private LocalDateTime lastTimestamp;

    public static Wallet of(UserId userId, Balances balances, LocalDateTime registeredTimestamp, LocalDateTime lastTimestamp) {
        return new Wallet(userId, balances, registeredTimestamp, lastTimestamp);
    }

    public void useBalance(Balance balance) {
        this.balances = this.balances.use(balance);
    }

    public Balance getBalanceByCurrency(Currency currency) {
        return this.balances.getByCurrency(currency);
    }
}
