package com.bricknet.workflow;

import com.bricknet.workflow.dto.NotificationDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Bricknet Workflow Api  ", version = "V 0.1.0", description = "take a look you can get it i hope "))
public class WorkflowApplication {


	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
		SpringApplication.run(WorkflowApplication.class, args);



	}



}
