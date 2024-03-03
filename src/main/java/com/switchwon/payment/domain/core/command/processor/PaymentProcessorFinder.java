package com.switchwon.payment.domain.core.command.processor;

import java.util.ArrayList;
import java.util.List;

public class PaymentProcessorFinder<FACTOR, RESULT> implements ProcessorFinder<FACTOR, RESULT> {
    private final List<SimpleProcessor<FACTOR, RESULT>> processorCandidates;

    public PaymentProcessorFinder() {
        this(new ArrayList<>());
    }

    public PaymentProcessorFinder(List<SimpleProcessor<FACTOR, RESULT>> processorCandidates) {
        this.processorCandidates = processorCandidates;
    }

    @Override
    public SimpleProcessor<FACTOR, RESULT> getProcessor(FACTOR factor) {
        return processorCandidates.stream()
                .filter(p -> p.support(factor))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public void addPolicyCandidate(SimpleProcessor<FACTOR, RESULT> policy) {
        this.processorCandidates.add(policy);
    }
}
