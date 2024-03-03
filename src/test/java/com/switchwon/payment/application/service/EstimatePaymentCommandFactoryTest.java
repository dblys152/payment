package com.switchwon.payment.application.service;

import com.switchwon.payment.application.usecase.model.EstimatePaymentRequest;
import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.estimation.EstimatePaymentCommand;
import com.switchwon.payment.domain.merchant.LoadMerchantPort;
import com.switchwon.payment.domain.merchant.Merchant;
import com.switchwon.payment.domain.merchant.MerchantId;
import com.switchwon.payment.refs.user.domain.LoadUserPort;
import com.switchwon.payment.refs.user.domain.User;
import com.switchwon.payment.refs.user.domain.UserId;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class EstimatePaymentCommandFactoryTest {
    private static final UserId USER_ID = UserId.of("99999");
    private static final MerchantId MERCHANT_ID = MerchantId.of("88888");

    @InjectMocks
    private EstimatePaymentCommandFactory sut;

    @Mock
    private LoadUserPort loadUserPort;
    @Mock
    private LoadMerchantPort loadMerchantPort;

    @Test
    void 결제_금액_예상_커맨드를_생성한다() {
        EstimatePaymentRequest request = getEstimatePaymentRequest();
        given(loadUserPort.findById(USER_ID)).willReturn(mock(User.class));
        given(loadMerchantPort.findById(MERCHANT_ID)).willReturn(mock(Merchant.class));

        EstimatePaymentCommand actual = sut.create(request);

        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> then(loadUserPort).should().findById(USER_ID),
                () -> then(loadMerchantPort).should().findById(MERCHANT_ID)
        );
    }

    private EstimatePaymentRequest getEstimatePaymentRequest() {
        EstimatePaymentRequest request = mock(EstimatePaymentRequest.class);
        given(request.getAmount()).willReturn(mock(BigDecimal.class));
        given(request.getCurrency()).willReturn(Currency.USD);
        given(request.getDestination()).willReturn(MERCHANT_ID.get());
        given(request.getUserId()).willReturn(USER_ID.get());
        return request;
    }

    @Test
    void 결제_금액_예상_커맨드를_생성_시_검증예_실패하면_에러를_반환한다() {
        EstimatePaymentRequest request = getScarceEstimatePaymentRequest();
        given(loadUserPort.findById(USER_ID)).willReturn(mock(User.class));
        given(loadMerchantPort.findById(MERCHANT_ID)).willReturn(mock(Merchant.class));

        assertAll(
                () -> assertThatThrownBy(() -> sut.create(request)).isInstanceOf(ConstraintViolationException.class),
                () -> then(loadUserPort).should().findById(USER_ID),
                () -> then(loadMerchantPort).should().findById(MERCHANT_ID)
        );
    }

    private EstimatePaymentRequest getScarceEstimatePaymentRequest() {
        EstimatePaymentRequest request = mock(EstimatePaymentRequest.class);
        given(request.getDestination()).willReturn(MERCHANT_ID.get());
        given(request.getUserId()).willReturn(USER_ID.get());
        return request;
    }
}