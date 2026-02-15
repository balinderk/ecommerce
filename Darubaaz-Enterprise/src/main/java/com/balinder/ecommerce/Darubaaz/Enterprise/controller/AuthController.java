package com.balinder.ecommerce.Darubaaz.Enterprise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balinder.ecommerce.Darubaaz.Enterprise.DTO.AuthResponse;
import com.balinder.ecommerce.Darubaaz.Enterprise.DTO.LoginRequest;
import com.balinder.ecommerce.Darubaaz.Enterprise.DTO.RegisterRequest;
import com.balinder.ecommerce.Darubaaz.Enterprise.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
	@Autowired
	private AuthService auths;
	
	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest rst)
	{
		auths.register(rst);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest lgr)
	{
		AuthResponse ap=auths.login(lgr);
		return new ResponseEntity<>(ap,HttpStatus.OK);
		
	}
	
	
	
	
	
}
