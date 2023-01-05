package com.kimani.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpServerErrorException;

/**
 * @Since 04/01/2023
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */

@Getter
@Setter
public class RestApiServerException extends HttpServerErrorException {
    private String path;

    public RestApiServerException(String path, HttpStatusCode statusCode, String statusText) {
        super(statusCode, statusText);
        setPath(path);
    }
}
