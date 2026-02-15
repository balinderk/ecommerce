package com.example.product.product.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ProductResponse {
	
	    private Long id;
	    private String name;
	    private String description;
	    private BigDecimal price;
	    private Integer stock;

}
