package com.bricknet.apigateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

public class CorsGlobalFilter{

}

//
//@Component
//public class CorsGlobalFilter extends AbstractGatewayFilterFactory<CorsGlobalFilter.Config> {
//
//    public CorsGlobalFilter() {
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            var response = exchange.getResponse();
//            var request = exchange.getRequest();
//            if (config.isPreflightRequest(request)) {
//                response.getHeaders().set("Access-Control-Allow-Origin", config.getAllowedOrigin());
//                response.getHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//                response.getHeaders().set("Access-Control-Max-Age", "3600");
//                response.getHeaders().set("Access-Control-Allow-Headers", "Authorization, Content-Type");
//                response.getHeaders().set("Access-Control-Allow-Credentials", "true");
//                return Mono.empty();
//            } else {
//                response.getHeaders().set("Access-Control-Allow-Origin", config.getAllowedOrigin());
//                response.getHeaders().set("Access-Control-Allow-Credentials", "true");
//                return chain.filter(exchange);
//            }
//        };
//    }
//
//    public static class Config {
//        private String allowedOrigin = "*";
//
//        public String getAllowedOrigin() {
//            return allowedOrigin;
//        }
//
//        public void setAllowedOrigin(String allowedOrigin) {
//            this.allowedOrigin = allowedOrigin;
//        }
//
//        public boolean isPreflightRequest(ServerHttpRequest request) {
//            return HttpMethod.OPTIONS.equals(request.getMethod()) &&
//                    request.getHeaders().containsKey("Access-Control-Request-Method");
//        }
//    }

