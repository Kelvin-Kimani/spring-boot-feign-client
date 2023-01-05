package com.kimani.auth.controllers;

import com.kimani.auth.dto.AuthRequest;
import com.kimani.auth.dto.AuthenticationResponse;
import com.kimani.auth.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Since 05/01/2023
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping()
    public AuthenticationResponse authenticate(@RequestBody AuthRequest request) {
        return authenticationService.authenticate(request);
    }
}
