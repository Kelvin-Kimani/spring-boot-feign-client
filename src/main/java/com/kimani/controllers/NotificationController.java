package com.kimani.controllers;

import com.africastalking.sms.Recipient;
import com.kimani.services.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Since 18/12/2022
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */


@RestController
@RequestMapping("/api/v1/notifications")
public record NotificationController(NotificationService notificationService) {


    @PostMapping
    public List<Recipient> sendSMS(@RequestParam String phoneNumbers, @RequestParam String message) {
        return notificationService.sendMessage(phoneNumbers.split(","), message);
    }
}
