package com.bricknet.apigateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private String SECRET_KEY = "462D4A614E645267556B58703272357538782F413F4428472B4B625065536856";



    public String extractEmail(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }


    public boolean validateToken(String jwt, String comparedJwtInRedis) {
        final String employeeCode= extractEmployeeCode(jwt);
        final String role = extractRole(jwt);

        return employeeCode.equals(extractEmployeeCode(comparedJwtInRedis)) && role.equals(extractRole(comparedJwtInRedis)) && !isTokenExpired(jwt);
    }

    public String extractRole(String jwt) {
        return extractClaim(jwt, c -> c.get("role", String.class));
    }

    public String extractEmployeeCode(String jwt) {
        return extractClaim(jwt, c -> c.get("employeeCode", String.class));
    }

    private <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }
}
