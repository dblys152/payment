package com.switchwon.payment.domain.core.command.handler;

import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.core.command.processor.PgApproveResult;
import com.switchwon.payment.domain.core.entity.Payment;
import com.switchwon.payment.domain.core.entity.RecordPaymentPort;
import com.switchwon.payment.domain.merchant.Merchant;
import com.switchwon.payment.refs.user.domain.User;
import com.switchwon.payment.refs.user.domain.UserId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApprovePaymentHandlerTest {
    @InjectMocks
    private ApprovePaymentHandler sut;

    @Mock
    private RecordPaymentPort recordPaymentPort;

    @Test
    void 결제_결과를_저장한다() {
        PgApproveResult result = mock(PgApproveResult.class);
        ApprovePaymentCommand command = getCommand();

        when(recordPaymentPort.save(any(Payment.class))).thenReturn(mock(Payment.class));
        Payment actual = sut.handle(result, command);

        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> then(recordPaymentPort).should().save(any(Payment.class))
        );
    }

    private ApprovePaymentCommand getCommand() {
        ApprovePaymentCommand command = mock(ApprovePaymentCommand.class);
        User user = mock(User.class);
        given(command.getUser()).willReturn(user);
        given(user.getUserId()).willReturn(mock(UserId.class));
        given(command.getMerchant()).willReturn(mock(Merchant.class));
        return command;
    }
}