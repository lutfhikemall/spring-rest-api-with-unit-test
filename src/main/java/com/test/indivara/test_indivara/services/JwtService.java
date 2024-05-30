package com.test.indivara.test_indivara.services;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.test.indivara.test_indivara.models.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.expirationDateInMs}")
    private int jwtExpirationMs;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String extractEmail(String token) {
        Claims claim = extractToken(token);

        String email = String.valueOf(claim.get("email"));

        return email;
    }

    public String generateToken(UserModel user) {
        return Jwts.builder().claim("id", user.getId())
                .claim("fullName", user.getFullName())
                .claim("email", user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token, String userEmail) {
        final String email = extractEmail(token);

        return (email.equals(userEmail));
    }

    private Claims extractToken(String token) {
        Claims claim = Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();

        return claim;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);

        return Keys.hmacShaKeyFor(keyBytes);
    }

}
