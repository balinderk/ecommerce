package com.balinder.notification.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balinder.notification.notification.dto.NotificationRequest;
import com.balinder.notification.notification.service.NotificationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationController {
	private final NotificationService notificationService;
	
	@PostMapping
    public ResponseEntity<Void> notify(@RequestBody NotificationRequest request) {
        notificationService.send(request);
        return ResponseEntity.ok().build();
    }
	

}
