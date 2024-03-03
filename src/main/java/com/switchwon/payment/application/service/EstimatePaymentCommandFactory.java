package com.switchwon.payment.application.service;

import com.switchwon.payment.application.usecase.model.EstimatePaymentRequest;
import com.switchwon.payment.domain.merchant.LoadMerchantPort;
import com.switchwon.payment.common.data.CommandFactory;
import com.switchwon.payment.domain.estimation.EstimatePaymentCommand;
import com.switchwon.payment.domain.merchant.Merchant;
import com.switchwon.payment.domain.merchant.MerchantId;
import com.switchwon.payment.refs.user.domain.LoadUserPort;
import com.switchwon.payment.refs.user.domain.User;
import com.switchwon.payment.refs.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Component
@RequiredArgsConstructor
public class EstimatePaymentCommandFactory implements
        CommandFactory<EstimatePaymentRequest, EstimatePaymentCommand> {
    private final LoadUserPort loadUserPort;
    private final LoadMerchantPort loadMerchantPort;

    @Override
    public EstimatePaymentCommand create(EstimatePaymentRequest request) {
        User user = loadUserPort.findById(UserId.of(request.getUserId()));
        Merchant merchant = loadMerchantPort.findById(MerchantId.of(request.getDestination()));

        return new EstimatePaymentCommand(
                request.getAmount(),
                request.getCurrency(),
                merchant,
                user
        );
    }
}
