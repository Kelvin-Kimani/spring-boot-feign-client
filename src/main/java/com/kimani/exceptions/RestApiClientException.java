package com.kimani.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @Since 04/01/2023
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */

@Getter
@Setter
public class RestApiClientException extends HttpClientErrorException {
    private String path;

    public RestApiClientException(String path, HttpStatusCode statusCode, String statusText) {
        super(statusCode, statusText);
        setPath(path);
    }
}
