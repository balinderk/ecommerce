package com.balinder.ecommerce.Darubaaz.Enterprise.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.balinder.ecommerce.Darubaaz.Enterprise.Entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private String secret="238947238789243789423789237894ru8eiyfh8347yr9823";
	
	private Long expiration=12123234567l;
	
	public String generateToken(User usr)
	{
		return Jwts.builder()
		.setSubject(usr.getEmail())
		.claim("user id", usr.getId())
		.claim("role",usr.getRole().name())
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis()+expiration))
		.signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
		.compact();
		
	}
	
	public long getExpirationInSeconds() {
		return expiration / 1000;
		}

}
