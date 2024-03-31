package com.switchwon.payment.presentation;

import com.switchwon.payment.application.usecase.ApprovalPaymentUseCase;
import com.switchwon.payment.application.usecase.model.ApprovePaymentRequest;
import com.switchwon.payment.common.data.CommandFactory;
import com.switchwon.payment.domain.core.command.ApprovePaymentCommand;
import com.switchwon.payment.domain.core.entity.Payment;
import com.switchwon.payment.presentation.model.PaymentModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/payments",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PaymentCommandController {
    private final CommandFactory<ApprovePaymentRequest, ApprovePaymentCommand> commandFactory;
    private final ApprovalPaymentUseCase approvalPaymentUseCase;

    @PostMapping("/approval")
    public ResponseEntity<PaymentModel> approvePayment(@Valid @RequestBody ApprovePaymentRequest request) {
        ApprovePaymentCommand command = commandFactory.create(request);

        Payment payment = approvalPaymentUseCase.approve(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                PaymentModel.fromDomain(payment));
    }
}
