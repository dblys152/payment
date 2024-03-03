package com.switchwon.payment.infrastructure.persistence;

import com.switchwon.payment.config.DataJpaConfig;
import com.switchwon.payment.domain.Currency;
import com.switchwon.payment.domain.core.entity.Payment;
import com.switchwon.payment.domain.core.entity.PaymentMethod;
import com.switchwon.payment.domain.core.entity.PaymentStatus;
import com.switchwon.payment.fixture.SupportPaymentFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = DataJpaConfig.class)
class PaymentRepositoryTest extends SupportPaymentFixture {
    @Autowired
    private PaymentRepository repository;
    @Autowired
    private MerchantRepository merchantRepository;

    private Payment payment;

    @BeforeEach
    void setUp() {
        merchantRepository.save(MERCHANT);
        payment = Payment.of(
                PAYMENT_ID, USER_ID, PaymentStatus.APPROVED, TIMESTAMP, APPROVAL_NUMBER, MERCHANT, PaymentMethod.CREDIT_CARD, Currency.USD, USD_200_DOLLARS, FEES, PAYMENT_DETAILS, TIMESTAMP);
    }

    @Test
    void save() {
        Payment actual = repository.save(payment);

        assertThat(actual).isNotNull();
    }

    @Test
    void findById() {
        Payment saved = repository.save(payment);

        Optional<Payment> actual = repository.findById(saved.getPaymentId());

        assertThat(actual).isPresent();
        assertThat(actual.get().getMerchant()).isNotNull();
    }
}