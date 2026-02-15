package com.balinder.order.order.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.balinder.order.order.dto.InternalUserResponse;

@FeignClient(name = "DARUBAAZUSER",path="/internal/users")
public interface UserClient {

	@GetMapping("/{id}")
    InternalUserResponse getUser(@PathVariable Long id);
	
}
