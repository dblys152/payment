package com.switchwon.payment.application.service;

import com.switchwon.payment.application.usecase.model.ApprovePaymentRequest;
import com.switchwon.payment.common.data.CommandFactory;
import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.core.entity.PaymentDetails;
import com.switchwon.payment.domain.merchant.LoadMerchantPort;
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
public class ApprovePaymentCommandFactory implements
        CommandFactory<ApprovePaymentRequest, ApprovePaymentCommand> {
    //private final LoadWalletPort loadWalletPort;
    private final LoadUserPort loadUserPort;
    private final LoadMerchantPort loadMerchantPort;

    @Override
    public ApprovePaymentCommand create(ApprovePaymentRequest request) {
        UserId userId = UserId.of(request.getUserId());

        // TODO: 결제 수단은 계좌와 연결된 카드인가? (wallets -> creditCards -> payments)
//        Wallet wallet = loadWalletPort.findById(userId);
//        Balance currentBalance = wallet.getBalanceByCurrency(request.getCurrency());
//        if (isInsufficientBalance(request, currentBalance)) {
//            throw new BadRequestException("잔액이 부족합니다.");
//        }
        
        User user = loadUserPort.findById(userId);
        Merchant merchant = loadMerchantPort.findById(MerchantId.of(request.getMerchantId()));
        ApprovePaymentRequest.PaymentDetailsRequest detailsRequest = request.getPaymentDetails();

        return new ApprovePaymentCommand(
                user,
                request.getAmount(),
                request.getCurrency(),
                merchant,
                request.getPaymentMethod(),
                PaymentDetails.of(detailsRequest.getCardNumber(), detailsRequest.getExpiryDate(), detailsRequest.getCvv())
        );
    }

//    private boolean isInsufficientBalance(ApprovePaymentRequest request, Balance currentBalance) {
//        return currentBalance.getAmount().compareTo(request.getAmount()) > 0;
//    }
}
