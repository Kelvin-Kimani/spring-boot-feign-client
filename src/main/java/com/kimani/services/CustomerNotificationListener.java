package com.kimani.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimani.configs.NatsTemplate;
import com.kimani.dto.MessageBuilder;
import io.nats.client.Message;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @Since 19/12/2022
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */

@Slf4j
@Service
public record CustomerNotificationListener(CustomerNotificationService notificationService, NatsTemplate natsTemplate) {

    private static final String CUSTOMER_SUBJECT = "customer.notification";

    @Bean
    public void subscribeNotifications() {
        natsTemplate.subscribe(CUSTOMER_SUBJECT, this::processNotifications);
    }

    private void processNotifications(Message message) {

        try {
            var content = new String(message.getData(), StandardCharsets.UTF_8);
            var messageBuilder = new ObjectMapper().readValue(content, MessageBuilder.class);

            notificationService.sendMessage(messageBuilder.getPhoneNumber().split(","), messageBuilder.getMessage());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
