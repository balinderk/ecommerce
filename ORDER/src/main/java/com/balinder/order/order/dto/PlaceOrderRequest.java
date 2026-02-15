package com.balinder.order.order.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PlaceOrderRequest {
	private Long userId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;

}
