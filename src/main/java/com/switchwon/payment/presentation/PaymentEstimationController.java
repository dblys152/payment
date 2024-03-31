package com.switchwon.payment.presentation;

import com.switchwon.payment.application.usecase.EstimatePaymentUseCase;
import com.switchwon.payment.application.usecase.model.EstimatePaymentRequest;
import com.switchwon.payment.common.data.CommandFactory;
import com.switchwon.payment.domain.estimation.EstimatePaymentCommand;
import com.switchwon.payment.domain.estimation.PaymentEstimation;
import com.switchwon.payment.presentation.model.PaymentEstimationModel;
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
@RequestMapping(value = "/api/payment/estimate",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PaymentEstimationController {
    private final CommandFactory<EstimatePaymentRequest, EstimatePaymentCommand> commandFactory;
    private final EstimatePaymentUseCase estimatePaymentUseCase;

    @PostMapping("")
    public ResponseEntity<PaymentEstimationModel> estimatePayment(@Valid @RequestBody EstimatePaymentRequest request) {
        EstimatePaymentCommand command = commandFactory.create(request);

        PaymentEstimation paymentEstimation = estimatePaymentUseCase.estimate(command);

        return ResponseEntity.status(HttpStatus.OK).body(new PaymentEstimationModel(
                paymentEstimation.getEstimatedTotal(), paymentEstimation.getFees(), paymentEstimation.getCurrency().name()));
    }
}
