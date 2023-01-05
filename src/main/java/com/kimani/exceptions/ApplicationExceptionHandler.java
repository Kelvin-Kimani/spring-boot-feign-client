package com.kimani.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Since 04/01/2023
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler({RestApiServerException.class})
    public ResponseEntity<?> handleRestClientException(RestApiServerException e) {
        var error = new ErrorResponse();
        error.setCode(e.getStatusCode().value());
        error.setMessage(e.getMessage());
        error.setStatus(e.getStatusText());
        error.setIsTechnical(false);
        error.setPath(e.getPath());
        return new ResponseEntity<>(error, e.getStatusCode());

    }

    @ExceptionHandler({RestApiClientException.class})
    public ResponseEntity<?> handleRestServerException(RestApiClientException e) {
        var error = new ErrorResponse();
        error.setCode(e.getStatusCode().value());
        error.setMessage(e.getMessage());
        error.setStatus(e.getStatusText());
        error.setIsTechnical(false);
        error.setPath(e.getPath());
        return new ResponseEntity<>(error, e.getStatusCode());
    }

}
