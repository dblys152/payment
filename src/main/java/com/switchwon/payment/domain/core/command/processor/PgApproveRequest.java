package com.switchwon.payment.domain.core.command.processor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.switchwon.payment.domain.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PgApproveRequest {
    @JsonProperty("merchant_uid")
    private String merchantUid;
    @JsonProperty("total_amount")
    private BigDecimal amount;
    @JsonProperty("currency")
    private Currency currency;
    @JsonProperty("name")
    private String name;
    @JsonProperty("buyer_name")
    private String buyerName;
    @JsonProperty("buyer_email")
    private String buyerEmail;
    @JsonProperty("buyer_tel")
    private String buyerTel;
}
