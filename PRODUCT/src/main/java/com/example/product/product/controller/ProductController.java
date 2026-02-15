package com.example.product.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.product.dto.ProductRequest;
import com.example.product.product.dto.ProductResponse;
import com.example.product.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(request));
	}

	@GetMapping
	public ResponseEntity<Page<ProductResponse>> getAll(Pageable pageable) {
		return ResponseEntity.ok(productService.getAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(productService.getById(id));
	}

}
