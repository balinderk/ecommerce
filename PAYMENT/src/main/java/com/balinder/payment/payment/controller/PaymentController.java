package com.balinder.payment.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balinder.payment.payment.dto.PaymentRequest;
import com.balinder.payment.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Void> pay(@RequestBody PaymentRequest request) {
        paymentService.processPayment(request);
        return ResponseEntity.ok().build();
    }
	
}
