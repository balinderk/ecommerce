package com.balinder.order.order.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.balinder.order.order.dto.InventoryRequest;

import jakarta.validation.Valid;

@FeignClient(name = "PRODUCT", path = "/internal/inventory")
public interface ProductClient {

	@PostMapping("/{productId}/reduce")
    void reduce(@PathVariable Long productId,
                                       @RequestBody @Valid InventoryRequest request);

	@PostMapping("/{productId}/restore")
    void restore(@PathVariable Long productId,
                                        @RequestBody @Valid InventoryRequest request);

}
