//package org.example.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Configuration
//public class Config {
//
////    @Bean
////    public WebClient.Builder webClientBuilder() {
////        return WebClient.builder()
////                .baseUrl("https://example.com") // Set your base URL here
////                .defaultHeader("Accept", "application/json")
////                .defaultHeader("Content-Type", "application/json");
////    }
////
////    @Bean
////    public WebClient webClient(WebClient.Builder webClientBuilder){
////        return WebClient.builder().build();
////    }
//
//    @Bean
//    public WebClient.Builder webClientBuilder(){
//        return WebClient.builder();
//    }
//
////    @Bean
////    public CommonsMultipartResolver multipartResolver() {
////        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
////        resolver.setDefaultEncoding("utf-8"); // Set the character encoding
////        return resolver;
////    }
//}
