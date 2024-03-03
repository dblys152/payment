package com.switchwon.payment.application.usecase.model;

import com.switchwon.payment.domain.Currency;
import lombok.Data;

@Data
public class GetBalanceRequest {
    private Currency currency = Currency.USD;
}
