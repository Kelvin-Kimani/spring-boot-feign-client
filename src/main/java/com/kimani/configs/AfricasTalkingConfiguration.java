package com.kimani.configs;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Since 18/12/2022
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */

@Configuration
public class AfricasTalkingConfiguration {

    @Value("${africastalking.apiKey}")
    private String apiKey;

    @Value("${africastalking.username}")
    private String username;

    @Bean
    public SmsService smsService() {
        AfricasTalking.initialize(username, apiKey);
        return AfricasTalking.getService(AfricasTalking.SERVICE_SMS);
    }

}
