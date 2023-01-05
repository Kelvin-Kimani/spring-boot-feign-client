package com.kimani.auth.services;

import com.kimani.auth.dto.AuthRequest;
import com.kimani.auth.dto.AuthenticationResponse;
import com.kimani.auth.dto.CustomUser;
import com.kimani.security.Role;
import com.kimani.security.jwt.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Since 05/01/2023
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthenticationResponse authenticate(AuthRequest request) {
        var user = CustomUser.builder()
                .email(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

}
