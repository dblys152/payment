package com.switchwon.payment.domain.core.command.processor;

import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.core.entity.PaymentMethod;
import com.switchwon.payment.refs.user.domain.Profile;
import org.springframework.stereotype.Service;

@Service
public class CreditCardPaymentProcessor extends PaymentProcessor {
    private final RequestPgApiPort requestPgApiPort;

    public CreditCardPaymentProcessor(RequestPgApiPort requestPgApiPort) {
        super(PaymentMethod.CREDIT_CARD);
        this.requestPgApiPort = requestPgApiPort;
    }

    @Override
    protected PaymentResult _execute(ApprovePaymentCommand factor) {
        Profile profile = factor.getUser().getProfile();
        PgApproveRequest request = new PgApproveRequest(
                factor.getMerchant().getMerchantId().get(),
                factor.getAmount(),
                factor.getCurrency(),
                factor.getName(),
                profile.getName(),
                profile.getEmail(),
                profile.getTel()
        );

        return requestPgApiPort.approve(request);
    }
}
