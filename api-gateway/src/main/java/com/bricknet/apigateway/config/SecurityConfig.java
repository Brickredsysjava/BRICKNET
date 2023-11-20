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
import com.bricknet.apigateway.config.ApiList;


@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        return authentication -> Mono.empty();
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
            return http
                    .csrf(ServerHttpSecurity.CsrfSpec::disable)
                    .cors(ServerHttpSecurity.CorsSpec::disable)
                    .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                            .pathMatchers(ApiList.openApi).permitAll()
                            .pathMatchers(ApiList.admin).hasAuthority("ADMIN")
//                            .pathMatchers("/eureka/**","super-admin/swagger-ui/index.html", "/auth/**").permitAll()
//                            .pathMatchers("/user/**","/communityPost/post/**", "/send/**", "user/profile/profileFromUserName/**" , "/api/broadcasting/**", "/api/to-do/**", "/media/**", "/suggestionPost/api/suggestions/**").permitAll()
//                            .pathMatchers("/suggestionPost/api/verification/**","/communityPost/admin/**").hasAuthority("ADMIN")
                            .anyExchange().authenticated())
                    .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                    .build();
    }
}
