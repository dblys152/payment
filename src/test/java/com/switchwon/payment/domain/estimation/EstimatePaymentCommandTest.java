package com.switchwon.payment.domain.estimation;

import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.merchant.FixedAmountFee;
import com.switchwon.payment.domain.merchant.FixedAmountFees;
import com.switchwon.payment.domain.merchant.Merchant;
import com.switchwon.payment.domain.merchant.MerchantId;
import com.switchwon.payment.refs.user.domain.Profile;
import com.switchwon.payment.refs.user.domain.User;
import com.switchwon.payment.refs.user.domain.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EstimatePaymentCommandTest {
    private EstimatePaymentCommand command;

    @BeforeEach
    void setUp() {
        Merchant merchant = Merchant.of(
                MerchantId.of("88888888"),
                FixedAmountFees.of(List.of(
                        FixedAmountFee.of(BigDecimal.valueOf(5), Currency.USD))));
        User user = User.of(
                UserId.of("9999999999"), Profile.of("사용자1", "test@mail.com", "010-7777-8888"));
        command = new EstimatePaymentCommand(BigDecimal.valueOf(150), Currency.USD, merchant, user);
    }

    @Test
    void 결제_금액_예상을_실행한다() {
        PaymentEstimation actual = command.execute();

        assertThat(actual).isNotNull();
    }
}