package com.balinder.order.order.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balinder.order.order.dto.PlaceOrderRequest;
import com.balinder.order.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
	@Autowired
	private  OrderService orderService;

    @PostMapping
    public ResponseEntity<Map<String, Long>> place(@RequestBody PlaceOrderRequest request) {
        Long orderId = orderService.placeOrder(request);
        return ResponseEntity.ok(Map.of("orderId", orderId));
    }

}
