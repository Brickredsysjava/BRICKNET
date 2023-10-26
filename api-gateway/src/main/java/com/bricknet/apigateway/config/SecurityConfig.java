package com.bricknet.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SecurityConfig {

    //private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        return authentication -> Mono.empty();
    }



    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                        .pathMatchers("/eureka/**","super-admin/swagger-ui/index.html","/auth-server/auth/getOtp/**","/auth-server/auth/checkOtp/**","/auth-server/auth/login/**", "/user/**" , "/auth/**").permitAll()
                        .pathMatchers("/communityPost/**", "/user/**", "/send/**", "/api/broadcasting/**", "/communityPost/**", "/api/**", "/media/**", "/suggestionPost/api/**").permitAll()
                        .pathMatchers("/profile/AllProfile/**").hasRole("ADMIN")
                        .anyExchange().authenticated()
                )
                //.addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

}
