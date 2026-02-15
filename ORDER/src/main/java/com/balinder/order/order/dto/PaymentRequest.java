package com.balinder.order.order.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {
	private Long orderId;
    private BigDecimal amount;

}
