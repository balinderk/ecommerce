package com.example.product.product.dto;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Component
@Data
public class ProductRequest {

	@NotBlank
	private String name;

	private String description;

	@NotNull
	private BigDecimal price;

	@NotNull
	private Integer stock;

}
