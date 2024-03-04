package com.switchwon.payment.presentation;

import com.switchwon.payment.application.usecase.GetBalanceQuery;
import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.wallet.Balance;
import com.switchwon.payment.presentation.model.BalanceModel;
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

@WebFluxTest(BalanceQueryController.class)
class BalanceQueryControllerTest {
    private static final UserId USER_ID = UserId.of("12345");

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private GetBalanceQuery getBalanceQuery;

    @Test
    void 잔액_조회_API() {
        Balance balance = mock(Balance.class);
        when(getBalanceQuery.getBalance(USER_ID, Currency.USD)).thenReturn(balance);
        when(balance.getAmount()).thenReturn(BigDecimal.valueOf(2000));
        when(balance.getCurrency()).thenReturn(Currency.USD);
        BalanceModel balanceModel = new BalanceModel(USER_ID.get(), balance.getAmount(), balance.getCurrency().name());

        webClient.get()
                .uri("/api/payment/balance/{userId}", USER_ID.get())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BalanceModel.class)
                .isEqualTo(balanceModel);
        then(getBalanceQuery).should().getBalance(USER_ID, Currency.USD);
    }
}