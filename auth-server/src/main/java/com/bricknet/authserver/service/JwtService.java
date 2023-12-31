package com.bricknet.authserver.service;

import com.bricknet.authserver.Dto.UserAuthInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    @Autowired
    private  RedisService redisService;

    private static String SECRET_KEY = "462D4A614E645267556B58703272357538782F413F4428472B4B625065536856";


    public String generateToken(UserAuthInfo user) {

        return Jwts.builder()
                .setSubject(user.getUuid())
                .claim("employeeName",user.getEmployeeName())
                .claim("role", user.getRole())
                .claim("employeeCode",user.getEmployeeCode())
                .claim("companyEmail",user.getCompanyEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24*60 * 60 * 1000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS384)
                .compact();
    }


    private SecretKey getSigningKey() {

        return
                Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }


}