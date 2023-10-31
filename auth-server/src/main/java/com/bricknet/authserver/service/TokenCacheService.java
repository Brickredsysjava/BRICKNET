package com.bricknet.authserver.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenCacheService {
    private static Map<String, String> tokenCache = new ConcurrentHashMap<>();

    public static void storeToken(String userId, String token) {
        tokenCache.put(userId, token);
    }

    public static String getToken(String userId) {
        return tokenCache.get(userId);
    }

    public static void removeToken(String userId) {
        tokenCache.remove(userId);
    }
}
