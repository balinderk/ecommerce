package com.example.product.product.GlobalExceptionHandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, String>> handle(RuntimeException ex) {
		Map<String,String> errorrsp=new HashMap<String, String>();
		errorrsp.put("error", ex.getMessage());
        return new ResponseEntity<>(errorrsp, HttpStatus.BAD_REQUEST);
    }
	

}
