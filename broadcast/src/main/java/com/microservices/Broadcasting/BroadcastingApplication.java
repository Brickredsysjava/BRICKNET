package com.microservices.Broadcasting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@SpringBootApplication
@EnableKafka
public class BroadcastingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BroadcastingApplication.class, args);
	}

}
