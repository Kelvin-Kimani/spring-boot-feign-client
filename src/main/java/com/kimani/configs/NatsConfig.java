package com.kimani.configs;

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class NatsConfig {

    @Value("${nats.server}")
    private String natsUrl;

    @Bean
    public Connection initConnection() throws IOException, InterruptedException {
        return Nats.connect(
                new Options.Builder()
                        .server(natsUrl)
                        .connectionListener((connection, events) -> log.info("NATS Channel connection {}", connection.getStatus()))
                        .build()
        );
    }

    @Bean
    public NatsTemplate natsTemplate(Connection connection) {
        return new NatsTemplate(connection);
    }
}
