package com.kimani.configs;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Since 19/12/2022
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */

@Configuration
public class Beans {

    @Value("${nats.server}")
    private String natsUrl;

    @Bean
    public Connection connection() {
        try {
            return Nats.connect(natsUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
