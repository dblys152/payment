package com.switchwon.payment.domain.core.command.processor;

import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.merchant.Merchant;
import com.switchwon.payment.domain.merchant.MerchantId;
import com.switchwon.payment.refs.user.domain.Profile;
import com.switchwon.payment.refs.user.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CreditCardPaymentProcessorTest {
    @InjectMocks
    private CreditCardPaymentProcessor sut;

    @Mock
    private RequestPgApiPort requestPgApiPort;

    @Test
    void 카드_결제를_실행한다() {
        ApprovePaymentCommand command = getCommand();
        given(requestPgApiPort.approve(any(PgApproveRequest.class))).willReturn(mock(PgApproveResult.class));

        PaymentResult actual = sut.execute(command);

        assertThat(actual).isNotNull();
        then(requestPgApiPort).should().approve(any(PgApproveRequest.class));
    }

    private ApprovePaymentCommand getCommand() {
        ApprovePaymentCommand command = mock(ApprovePaymentCommand.class);
        User user = mock(User.class);
        given(command.getUser()).willReturn(user);
        given(user.getProfile()).willReturn(mock(Profile.class));
        Merchant merchant = mock(Merchant.class);
        given(merchant.getMerchantId()).willReturn(mock(MerchantId.class));
        given(command.getMerchant()).willReturn(merchant);
        return command;
    }
}