package com.kimani.services;

import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimani.dto.Customer;
import com.kimani.dto.MessageBuilder;
import io.nats.client.Connection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Since 18/12/2022
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */


@Slf4j
@Service
public record CustomerNotificationService(CustomerService customerService,
                                          Connection connection,
                                          SmsService smsService) implements INotificationService {


    private final static String CUSTOMER_SUBJECT = "customer.notification";

    @Override
    public List<Recipient> sendMessage(String[] to, String message) {
        try {
            log.info("Sending notification {} to {}", message, to);
            return smsService.send(message, to, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void bulkPublishMessage() {
        List<Customer> customers = customerService.getAllCustomers();

        log.info("Publishing message to the following customers -> {}", customers.toString());
        customers.stream()
                .map(MessageBuilder::new)
                .forEach(messageBuilder -> {
                    try {

                        String value = new ObjectMapper().writeValueAsString(messageBuilder);
                        connection.publish(CUSTOMER_SUBJECT, value.getBytes());

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });
    }
}
