package com.balinder.notification.notification.service;

import org.springframework.stereotype.Service;

import com.balinder.notification.notification.dto.NotificationRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationService {

	public void send(NotificationRequest request) {
        // Mock email / SMS sending
        log.info("Sending notification to {} : {}", request.getEmail(), request.getMessage());
    }
	
}
