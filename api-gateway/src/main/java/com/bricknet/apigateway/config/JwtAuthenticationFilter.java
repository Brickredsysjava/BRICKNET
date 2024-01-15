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

import java.util.List;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Component
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class JwtAuthenticationFilter implements WebFilter{
    private final JwtService jwtService;

    @Autowired
    private final RedisService redisService;
    @Autowired
    private final JwtMap jwtMap;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String jwt = extractTokenFromRequest(exchange);

        String path = exchange.getRequest().getPath().toString();
//        log.info("Path: " + path);
//        log.info("This is jwt -- ------");
//        log.info(jwt);
        if (jwt != null) {

            String empcode = jwtService.extractEmployeeCode(jwt);
            //log.warn("This is empcode ---------------------");
            log.warn(empcode);

            Mono<String> monres = jwtMap.getByjwt(empcode);
            jwtMap.setToken(monres);

            String comparedJwtInJWTMap = String.valueOf(jwtMap.getByjwt(empcode));
//            log.warn("This is comparedJwtInJwtMap   ---------------------");
//            log.warn(comparedJwtInJWTMap);

            String email = jwtService.extractEmail(jwt);

//            log.warn("This is email " + email);
            String role = jwtService.extractRole(jwt);

//            log.warn("This is role " + role);

            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(jwtService.extractRole(jwt)));
//            log.warn(authorities.toString());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    email, null, authorities
            );


            return monres.flatMap( thisis -> {

                if(thisis != null && thisis.equals(jwt)) {
//                    if(!role.equals("ADMIN")) {
//                        return null;
//                    }
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
                return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(usernamePasswordAuthenticationToken));
            });
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
