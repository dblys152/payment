package com.switchwon.payment.infrastructure.persistence;

import com.switchwon.payment.domain.core.entity.Payment;
import com.switchwon.payment.domain.core.entity.PaymentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, PaymentId> {
    @Query("SELECT p " +
            "FROM Payment p " +
            "JOIN FETCH p.merchant m " +
            "WHERE p.paymentId = :paymentId")
    Optional<Payment> findById(@Param("paymentId") PaymentId paymentId);
}
