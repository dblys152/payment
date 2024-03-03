package com.switchwon.payment.domain.core.command.processor;

public interface SimpleProcessor<FACTOR, RESULT> {
    boolean support(FACTOR factor);

    RESULT execute(FACTOR factor);
}
