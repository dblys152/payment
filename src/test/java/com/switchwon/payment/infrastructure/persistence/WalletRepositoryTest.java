package com.switchwon.payment.infrastructure.persistence;

import com.switchwon.payment.config.DataJpaConfig;
import com.switchwon.payment.domain.wallet.Wallet;
import com.switchwon.payment.fixture.SupportWalletFixture;
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
class WalletRepositoryTest extends SupportWalletFixture {
    @Autowired
    private WalletRepository repository;

    private Wallet wallet;

    @BeforeEach
    void setUp() {
        wallet = Wallet.of(USER_ID, BALANCES, TIMESTAMP, TIMESTAMP);
    }

    @Test
    void save() {
        Wallet actual = repository.save(wallet);

        assertThat(actual).isNotNull();
    }

    @Test
    void findById() {
        Wallet saved = repository.save(wallet);

        Optional<Wallet> actual = repository.findById(saved.getUserId());

        assertThat(actual).isPresent();
    }
}