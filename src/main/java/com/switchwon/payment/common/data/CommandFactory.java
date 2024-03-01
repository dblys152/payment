package com.switchwon.payment.common.data;

public interface CommandFactory<R, C> {
    C create(R request);
}
