package com.balinder.ecommerce.Darubaaz.Enterprise.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.balinder.ecommerce.Darubaaz.Enterprise.DTO.AuthResponse;
import com.balinder.ecommerce.Darubaaz.Enterprise.DTO.LoginRequest;
import com.balinder.ecommerce.Darubaaz.Enterprise.DTO.RegisterRequest;
import com.balinder.ecommerce.Darubaaz.Enterprise.Entity.Role;
import com.balinder.ecommerce.Darubaaz.Enterprise.Entity.User;
import com.balinder.ecommerce.Darubaaz.Enterprise.Repository.UserRepository;
import com.balinder.ecommerce.Darubaaz.Enterprise.util.JwtUtil;

@Service
public class AuthService {

	@Autowired
	private UserRepository usr;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;

	public void register(RegisterRequest req) {

		if (usr.existsByEmail(req.getEmail())) {
			throw new RuntimeException("User already exists");
		}

		User user = User.builder().name(req.getName()).email(req.getEmail())
				.password(passwordEncoder.encode(req.getPassword())).role(Role.CUSTOMER).createdAt(LocalDateTime.now())
				.build();
		usr.save(user);

	}

	public AuthResponse login(LoginRequest lgr)
	{
		User user=usr.findByEmail(lgr.getEmail()).orElseThrow(()->new RuntimeException("Invalid Credentials"));
		
		if(!passwordEncoder.matches(lgr.getPassword(), user.getPassword()))
		{
			throw new RuntimeException("Invalid Credentials");
		}

		return AuthResponse.builder()
				.token(jwtUtil.generateToken(user))
				.expiresIn(jwtUtil.getExpirationInSeconds())
				.build();
	}

}
