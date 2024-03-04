package com.switchwon.payment.presentation;

import com.switchwon.payment.application.usecase.ApprovalPaymentUseCase;
import com.switchwon.payment.application.usecase.model.ApprovePaymentRequest;
import com.switchwon.payment.common.data.CommandFactory;
import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.core.entity.Payment;
import com.switchwon.payment.domain.core.entity.PaymentMethod;
import com.switchwon.payment.domain.core.entity.PaymentStatus;
import com.switchwon.payment.fixture.SupportPaymentFixture;
import com.switchwon.payment.presentation.model.PaymentModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebFluxTest(PaymentCommandController.class)
class PaymentCommandControllerTest extends SupportPaymentFixture {
    @Autowired
    private WebTestClient webClient;

    @MockBean
    private CommandFactory<ApprovePaymentRequest, ApprovePaymentCommand> commandFactory;
    @MockBean
    private ApprovalPaymentUseCase approvalPaymentUseCase;

    @Test
    void 결제_승인_API() {
        ApprovePaymentRequest request = getApprovePaymentRequest();
        ApprovePaymentCommand command = mock(ApprovePaymentCommand.class);
        when(commandFactory.create(request)).thenReturn(command);
        Payment payment = getPayment();
        when(approvalPaymentUseCase.approve(command)).thenReturn(payment);

        PaymentModel paymentModel = PaymentModel.fromDomain(payment);

        webClient.post()
                .uri("/api/payments/approval")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(PaymentModel.class)
                .isEqualTo(paymentModel);
        then(commandFactory).should().create(request);
        then(approvalPaymentUseCase).should().approve(command);
    }

    private ApprovePaymentRequest getApprovePaymentRequest() {
        ApprovePaymentRequest request = new ApprovePaymentRequest();
        request.setUserId(USER_ID.get());
        request.setAmount(USD_200_DOLLARS);
        request.setCurrency(Currency.USD);
        request.setMerchantId(MERCHANT_ID.get());
        request.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        request.setPaymentDetails(new ApprovePaymentRequest.PaymentDetailsRequest(CARD_NUMBER, EXPIRY_DATE, CVV));
        return request;
    }

    private Payment getPayment() {
        return Payment.of(
                PAYMENT_ID, USER_ID, PaymentStatus.APPROVED, TIMESTAMP, APPROVAL_NUMBER, MERCHANT, PaymentMethod.CREDIT_CARD, Currency.USD, USD_200_DOLLARS, FEES, PAYMENT_DETAILS, TIMESTAMP);
    }
}