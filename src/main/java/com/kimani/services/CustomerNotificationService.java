package com.kimani.services;

import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimani.configs.NatsTemplate;
import com.kimani.dto.Customer;
import com.kimani.dto.MessageBuilder;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Since 18/12/2022
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */

@Slf4j
@Service
public record CustomerNotificationService(CustomerService customerService,
                                          NatsTemplate natsTemplate,
                                          SmsService smsService) implements INotificationService {

    private static final String CUSTOMER_SUBJECT = "customer.notification";

    @Override
    public List<Recipient> sendMessage(String[] to, String message) {
        try {
            log.info("Sending notification {} to {}", message, to);
            return smsService.send(message, to, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void bulkPublishMessage() {
        List<Customer> customers = customerService.getAllCustomers();

        log.info("Publishing message to the following customers -> {}", customers);
        customers.stream()
                .map(MessageBuilder::new)
                .forEach(messageBuilder -> {
                    try {
                        natsTemplate.convertAndSend(CUSTOMER_SUBJECT, new ObjectMapper().writeValueAsString(messageBuilder).getBytes());
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });
    }
}
