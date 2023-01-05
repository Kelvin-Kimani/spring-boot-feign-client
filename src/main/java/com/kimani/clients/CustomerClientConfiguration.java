package com.kimani.clients;

import com.kimani.clients.exceptions.CustomErrorHandler;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

/**
 * @Since 04/01/2023
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */
public class CustomerClientConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorHandler();
    }
}
