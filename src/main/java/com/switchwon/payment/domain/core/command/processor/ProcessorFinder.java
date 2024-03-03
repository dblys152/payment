package com.switchwon.payment.domain.core.command.processor;

public interface ProcessorFinder<FACTOR, RESULT> {
    SimpleProcessor<FACTOR, RESULT> getProcessor(FACTOR factor);
}
