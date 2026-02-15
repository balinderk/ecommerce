package com.balinder.payment.payment.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.balinder.payment.payment.dto.PaymentRequest;
import com.balinder.payment.payment.entity.PaymentStatus;
import com.balinder.payment.payment.entity.Payments;
import com.balinder.payment.payment.repository.PaymentRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	
	private final PaymentRepo paymentRepository;

    public void processPayment(PaymentRequest request) {

        // Simulate payment failure for large amounts
        boolean success = request.getAmount().compareTo(BigDecimal.valueOf(10000)) < 0;

        Payments payment = Payments.builder()
            .orderId(request.getOrderId())
            .amount(request.getAmount())
            .status(success ? PaymentStatus.SUCCESS : PaymentStatus.FAILED)
            .createdAt(LocalDateTime.now())
            .build();

        paymentRepository.save(payment);

        if (!success) {
            throw new RuntimeException("Payment failed");
        }
    }
	

}
