package com.example.product.product.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Component
@Data
public class InventoryRequest {
	@NotNull
	private Integer quantity;

}
