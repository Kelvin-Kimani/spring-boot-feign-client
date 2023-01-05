package com.kimani.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @Since 12/12/2022
 * @Author: Kimani Kelvin
 * @Contact: kelvinkimaniapps@gmail.com
 */

@Component
public class JWTService {
    private static final String JWT_SIGNING_KEY = "6eu2dRYVI0W2Djcsnbqk260IjQkgtTc3adN3S5cOwhfX4ARtmFtlM40jAnJyKM9lpkw6wWGdamxutphjOFujgcvepaXxeairfpn6IzMw4gOc4lbKksEuSb4AQRfq4kAabqQOtf6MkLpWFhgh8GY5STaYyX8n4tOIQHxkTjJFnfdh7OxwOEJHBwTQbz57avLWvn1BeTHQrmXQFU59H2KkBV8dUUgNFQVscChoKLQDj46EFcZrc8zOYiJkdv22JgHEOMdOc6KnHsJCVD8m6HANR0IXCghCFDUTLIHoPVuujrTK40ndyRABlXSXzG7zwrMg2I2M";

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        return createToken(new HashMap<>(), userDetails);
    }

    public String createToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SIGNING_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
