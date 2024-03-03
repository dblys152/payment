package com.switchwon.payment.infrastructure;

import com.switchwon.payment.refs.user.domain.LoadUserPort;
import com.switchwon.payment.refs.user.domain.Profile;
import com.switchwon.payment.refs.user.domain.User;
import com.switchwon.payment.refs.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MockUserApiAdapter implements LoadUserPort {
    @Override
    public User findById(UserId userId) {
        return User.of(
                userId, Profile.of("주문자1", "mock@mail.com", "010-7777-8888"));
    }
}
