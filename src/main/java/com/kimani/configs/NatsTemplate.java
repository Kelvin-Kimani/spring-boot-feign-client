package com.kimani.configs;

import io.nats.client.Connection;
import io.nats.client.MessageHandler;

public class NatsTemplate {

    private final Connection connection;

    public NatsTemplate(Connection connection) {
        this.connection = connection;
    }

    public void convertAndSend(String subject, byte[] message) {
        connection.publish(subject, message);
    }

    public void subscribe(String subject, MessageHandler messageHandler) {
        var dispatcher = connection.createDispatcher(messageHandler);
        dispatcher.subscribe(subject);
    }
}
