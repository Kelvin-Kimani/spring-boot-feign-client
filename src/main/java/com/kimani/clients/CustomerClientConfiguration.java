package com.kimani.clients;

import com.kimani.clients.exceptions.CustomErrorHandler;
import com.kimani.security.jwt.JWTService;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static java.util.Objects.nonNull;

/**
 * @Since 04/01/2023
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */

@RequiredArgsConstructor
public class CustomerClientConfiguration {

    private final JWTService jwtService;
    private final CustomErrorHandler customErrorHandler;


    @Bean
    public ErrorDecoder errorDecoder() {
        return customErrorHandler;
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * Re-authenticates before making external call
     */
    @Bean
    public RequestInterceptor requestInterceptor() {

        return requestTemplate -> {
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            if (nonNull(authentication)) {
                var currentLoggedInUser = (UserDetails) authentication.getPrincipal();
                String authToken = jwtService.generateToken(currentLoggedInUser);
                requestTemplate.header("Authorization", "Bearer " + authToken);
            }
        };
    }
}
