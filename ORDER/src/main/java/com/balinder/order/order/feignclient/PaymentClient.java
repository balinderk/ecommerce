package com.balinder.order.order.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.balinder.order.order.dto.PaymentRequest;

@FeignClient(name = "PAYMENT",path="/payments")
public interface PaymentClient {

	@PostMapping
    void pay(@RequestBody PaymentRequest request);
	
}
