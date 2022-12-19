package com.kimani.services;


import com.africastalking.sms.Recipient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimani.dto.MessageBuilder;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @Since 19/12/2022
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */

@Slf4j
@Service
public record CustomerNotificationListener(CustomerNotificationService notificationService, Connection connection) {

    private final static String CUSTOMER_SUBJECT = "customer.notification";

    @Bean
    public void subscribeCustomerNotificationSubject() {
        Dispatcher dispatcher = connection.createDispatcher(message -> {

            var content = new String(message.getData(), StandardCharsets.UTF_8);
            System.out.printf("Received message [%s] from subject [%s]%n",
                    content, message.getSubject());

            try {

                var messageBuilder = new ObjectMapper().readValue(content, MessageBuilder.class);
                List<Recipient> recipients = notificationService.sendMessage(messageBuilder.getPhoneNumber().split(","), messageBuilder.getMessage());
                log.info("" + recipients);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        dispatcher.subscribe(CUSTOMER_SUBJECT);
    }
}
