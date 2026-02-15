package com.balinder.order.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryRequest {
	private Integer quantity;
}
