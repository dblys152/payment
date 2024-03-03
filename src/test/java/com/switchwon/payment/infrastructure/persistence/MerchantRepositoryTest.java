package com.switchwon.payment.infrastructure.persistence;

import com.switchwon.payment.config.DataJpaConfig;
import com.switchwon.payment.domain.merchant.Merchant;
import com.switchwon.payment.fixture.SupportMerchantFixture;
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
class MerchantRepositoryTest extends SupportMerchantFixture {
    @Autowired
    private MerchantRepository repository;

    private Merchant merchant;

    @BeforeEach
    void setUp() {
        merchant = Merchant.of(MERCHANT_ID, FIXED_AMOUNT_FEES);
    }

    @Test
    void save() {
        Merchant actual = repository.save(merchant);

        assertThat(actual).isNotNull();
    }

    @Test
    void findById() {
        Merchant saved = repository.save(merchant);

        Optional<Merchant> actual = repository.findById(saved.getMerchantId());

        assertThat(actual).isPresent();
    }
}