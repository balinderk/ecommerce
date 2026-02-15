package com.example.product.product.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.product.product.dto.ProductRequest;
import com.example.product.product.dto.ProductResponse;
import com.example.product.product.entity.Product;
import com.example.product.product.repository.ProductRepo;
@Service
public class ProductService {
	@Autowired
	private ProductRepo productRepository;

	public ProductResponse create(ProductRequest request) {
		Product product = Product.builder().name(request.getName()).description(request.getDescription())
				.price(request.getPrice()).stock(request.getStock()).createdAt(LocalDateTime.now()).build();

		productRepository.save(product);
		return map(product);
	}

	public Page<ProductResponse> getAll(Pageable pageable) {
		return productRepository.findAll(pageable).map(this::map);
	}

	public ProductResponse getById(Long id) {
		return productRepository.findById(id).map(this::map)
				.orElseThrow(() -> new RuntimeException("Product not found"));
	}

	public void reduceStock(Long productId, Integer quantity) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		if (product.getStock() < quantity) {
			throw new RuntimeException("Insufficient stock");
		}

		product.setStock(product.getStock() - quantity);
		productRepository.save(product);
	}

	public void restoreStock(Long productId, Integer quantity) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		product.setStock(product.getStock() + quantity);
		productRepository.save(product);
	}

	private ProductResponse map(Product product) {
		return ProductResponse.builder().id(product.getId()).name(product.getName())
				.description(product.getDescription()).price(product.getPrice()).stock(product.getStock()).build();
	}

}
