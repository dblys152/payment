package com.switchwon.payment.refs.user.domain;

import com.switchwon.payment.refs.user.domain.User;
import com.switchwon.payment.refs.user.domain.UserId;

public interface LoadUserPort {
    User findById(UserId userId);
}
