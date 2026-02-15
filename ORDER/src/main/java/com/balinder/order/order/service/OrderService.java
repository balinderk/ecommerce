package com.balinder.order.order.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balinder.order.order.dto.InventoryRequest;
import com.balinder.order.order.dto.PaymentRequest;
import com.balinder.order.order.dto.PlaceOrderRequest;
import com.balinder.order.order.entity.Order;
import com.balinder.order.order.entity.OrderItem;
import com.balinder.order.order.entity.OrderStatus;
import com.balinder.order.order.feignclient.PaymentClient;
import com.balinder.order.order.feignclient.ProductClient;
import com.balinder.order.order.feignclient.UserClient;
import com.balinder.order.order.repository.OrderItemRepo;
import com.balinder.order.order.repository.OrderRepo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderService {
	@Autowired
	private  OrderRepo orderRepository;
    @Autowired
	private  OrderItemRepo itemRepository;
    @Autowired
    private  ProductClient productClient;
    @Autowired
    private  UserClient userClient;
    @Autowired
    private  PaymentClient paymentClient;
    
    
    
    @CircuitBreaker(name = "PRODUCT", fallbackMethod = "fallback")
    public Long placeOrder(PlaceOrderRequest request) {

        userClient.getUser(request.getUserId()); // validate user

        Order order = Order.builder()
            .userId(request.getUserId())
            .status(OrderStatus.CREATED)
            .createdAt(LocalDateTime.now())
            .totalAmount(request.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())))
            .build();

        orderRepository.save(order);

        try {
            productClient.reduce(request.getProductId(),
                new InventoryRequest(request.getQuantity()));

            paymentClient.pay(PaymentRequest.builder()
                .orderId(order.getId())
                .amount(order.getTotalAmount())
                .build());

            order.setStatus(OrderStatus.PAYMENT_COMPLETED);
        } catch (Exception ex) {
            productClient.restore(request.getProductId(),
                new InventoryRequest(request.getQuantity()));

            order.setStatus(OrderStatus.FAILED);
        }
        orderRepository.save(order);

        OrderItem item = OrderItem.builder()
            .orderId(order.getId())
            .productId(request.getProductId())
            .quantity(request.getQuantity())
            .price(request.getPrice())
            .build();

        itemRepository.save(item);

        return order.getId();
    }
    
    public Long fallback(PlaceOrderRequest request, Throwable t) {
        throw new RuntimeException("Product service unavailable");
    }
	

}
