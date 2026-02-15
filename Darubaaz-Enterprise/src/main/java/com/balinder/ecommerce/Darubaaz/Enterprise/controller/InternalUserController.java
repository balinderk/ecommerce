package com.balinder.ecommerce.Darubaaz.Enterprise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balinder.ecommerce.Darubaaz.Enterprise.DTO.InternalUserResponse;
import com.balinder.ecommerce.Darubaaz.Enterprise.Entity.User;
import com.balinder.ecommerce.Darubaaz.Enterprise.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/internal/users")
@RequiredArgsConstructor
public class InternalUserController {

	private final UserRepository userRepository;

	@GetMapping("/{id}")
	public ResponseEntity<InternalUserResponse> getUser(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		InternalUserResponse inr = InternalUserResponse.builder().id(user.getId()).email(user.getEmail())
				.role(user.getRole().name()).build();
		return new ResponseEntity<>(inr, HttpStatus.OK);
	}
}
