package com.switchwon.payment.presentation;

import com.switchwon.payment.application.usecase.EstimatePaymentUseCase;
import com.switchwon.payment.application.usecase.model.EstimatePaymentRequest;
import com.switchwon.payment.common.data.CommandFactory;
import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.estimation.EstimatePaymentCommand;
import com.switchwon.payment.domain.estimation.PaymentEstimation;
import com.switchwon.payment.domain.merchant.MerchantId;
import com.switchwon.payment.presentation.model.PaymentEstimationModel;
import com.switchwon.payment.refs.user.domain.UserId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebFluxTest(PaymentEstimationController.class)
class PaymentEstimationControllerTest {
    private static final UserId USER_ID = UserId.of("12345");
    private static final MerchantId MERCHANT_ID = MerchantId.of("merchantId123");
    private static final BigDecimal USD_150_DOLLARS = BigDecimal.valueOf(150);
    private static final BigDecimal ESTIMATED_TOTAL = BigDecimal.valueOf(155);
    private static final BigDecimal FEES = BigDecimal.valueOf(5);

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private CommandFactory<EstimatePaymentRequest, EstimatePaymentCommand> commandFactory;
    @MockBean
    private EstimatePaymentUseCase estimatePaymentUseCase;

    @Test
    void 결제_예상_결과_조회_API() {
        EstimatePaymentRequest request = getEstimatePaymentRequest();
        EstimatePaymentCommand command = mock(EstimatePaymentCommand.class);
        when(commandFactory.create(request)).thenReturn(command);
        PaymentEstimation paymentEstimation = getPaymentEstimation();
        when(estimatePaymentUseCase.estimate(command)).thenReturn(paymentEstimation);

        PaymentEstimationModel paymentEstimationModel = new PaymentEstimationModel(ESTIMATED_TOTAL, FEES, request.getCurrency().name());

        webClient.post()
                .uri("/api/payment/estimate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PaymentEstimationModel.class)
                .isEqualTo(paymentEstimationModel);
        then(commandFactory).should().create(request);
        then(estimatePaymentUseCase).should().estimate(command);
    }

    private EstimatePaymentRequest getEstimatePaymentRequest() {
        EstimatePaymentRequest request = new EstimatePaymentRequest();
        request.setAmount(USD_150_DOLLARS);
        request.setCurrency(Currency.USD);
        request.setDestination(MERCHANT_ID.get());
        request.setUserId(USER_ID.get());
        return request;
    }

    private PaymentEstimation getPaymentEstimation() {
        return PaymentEstimation.create(USD_150_DOLLARS, Currency.USD, ESTIMATED_TOTAL, FEES);
    }
}