package com.switchwon.payment.presentation;

import com.switchwon.payment.application.usecase.GetBalanceQuery;
import com.switchwon.payment.application.usecase.model.GetBalanceRequest;
import com.switchwon.payment.domain.wallet.Balance;
import com.switchwon.payment.presentation.model.BalanceModel;
import com.switchwon.payment.refs.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/payment/balance",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BalanceQueryController {
    private final GetBalanceQuery getBalanceQuery;

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<BalanceModel>> getBalance(
            @PathVariable("userId") String userId,
            GetBalanceRequest request) {
        return Mono.fromCallable(() -> {
                    Balance balance = getBalanceQuery.getBalance(UserId.of(userId), request.getCurrency());
                    return new BalanceModel(userId, balance.getAmount(), balance.getCurrency().name());
                })
                .map(balanceModel ->  ResponseEntity.status(HttpStatus.OK).body(balanceModel));
    }
}
