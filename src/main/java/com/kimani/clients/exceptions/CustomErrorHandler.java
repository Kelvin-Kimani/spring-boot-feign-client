package com.kimani.clients.exceptions;

import com.kimani.exceptions.RestApiClientException;
import com.kimani.exceptions.RestApiServerException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @Since 04/01/2023
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */

@Configuration
public class CustomErrorHandler implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        String requestUrl = response.request().url();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus.is5xxServerError()) {
            return new RestApiServerException(requestUrl, responseStatus, responseStatus.getReasonPhrase());
        } else if (responseStatus.is4xxClientError()) {
            return new RestApiClientException(requestUrl, responseStatus, responseStatus.getReasonPhrase());
        } else {
            return new Exception("Generic exception");
        }
    }
}
