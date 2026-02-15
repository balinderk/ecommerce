package com.example.product.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.product.dto.InventoryRequest;
import com.example.product.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/inventory")
public class InternalInventoryController {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/{productId}/reduce")
    public ResponseEntity<Void> reduce(@PathVariable Long productId,
                                       @RequestBody @Valid InventoryRequest request) {
        productService.reduceStock(productId, request.getQuantity());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{productId}/restore")
    public ResponseEntity<Void> restore(@PathVariable Long productId,
                                        @RequestBody @Valid InventoryRequest request) {
        productService.restoreStock(productId, request.getQuantity());
        return ResponseEntity.ok().build();
    }
	

}
