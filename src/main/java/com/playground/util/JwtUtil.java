package com.playground.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${registeredUser.username}")
    private String registeredUserName;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.token.validity}")
    private long tokenValidity;

    public String generateJwt(String username) {
        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + tokenValidity * 1000))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean validateJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).build().parseSignedClaims(token).getPayload();
        return claims.getSubject().equals(registeredUserName) && new Date().before(claims.getExpiration());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).build().parseSignedClaims(token).getPayload().getSubject();
    }
}
