package com.bricknet.authserver.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {
    private static String SECRET_KEY = "462D4A614E645267556B58703272357538782F413F4428472B4B625065536856";





    public static Claims extractClaims(String jwtToken) {

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwtToken)
                    .getBody();

            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String extractClaim(String authorizationHeader,String claimName) {
        String jwtToken = extractBearerToken(authorizationHeader);

        Claims claims = extractClaims(jwtToken);

        if (claims != null) {
            return (String) claims.get(claimName);
        } else {
            System.out.println("Failed to extract claims from JWT.");
        }
        return "no claim found with name "+claimName;
    }
    public static String extractBearerToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);  // "Bearer " is 7 characters
        }

        return null;
    }
}