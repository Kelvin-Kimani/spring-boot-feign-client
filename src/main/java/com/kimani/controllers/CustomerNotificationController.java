package com.kimani.controllers;

import com.africastalking.sms.Recipient;
import com.kimani.services.CustomerNotificationService;
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
public record CustomerNotificationController(CustomerNotificationService customerNotificationService) {


    @PostMapping("/publish")
    public void bulkPublishMessages() {
        customerNotificationService.bulkPublishMessage();
    }

    @PostMapping
    public List<Recipient> sendSMS(@RequestParam String phoneNumbers, @RequestParam String message) {
        return customerNotificationService.sendMessage(phoneNumbers.split(","), message);
    }
}
