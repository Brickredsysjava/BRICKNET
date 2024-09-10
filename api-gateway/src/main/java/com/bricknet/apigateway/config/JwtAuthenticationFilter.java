package com.bricknet.apigateway.config;
import com.bricknet.apigateway.service.JwtMap;
import com.bricknet.apigateway.service.JwtService;
import com.bricknet.apigateway.service.RedisService;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Component
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class JwtAuthenticationFilter implements WebFilter{
    private final JwtService jwtService;

    private static Jedis jedis = new JedisPool("192.168.1.9", 6379).getResource();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String jwt = extractTokenFromRequest(exchange);

        String path = exchange.getRequest().getPath().toString();

        if (jwt != null) {

            String comparedJwtInRedis = jedis.get(jwtService.extractEmployeeCode(jwt));
            if(comparedJwtInRedis != null) {
                if (jwtService.validateToken(jwt, comparedJwtInRedis)) {
                    String email = jwtService.extractEmail(jwt);
                    String role = jwtService.extractRole(jwt);
                    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(jwtService.extractRole(jwt)));
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            email, null, authorities
                    );
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(usernamePasswordAuthenticationToken));
                }
            }
        }

        return chain.filter(exchange);
    }
    private String extractTokenFromRequest(ServerWebExchange exchange) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
