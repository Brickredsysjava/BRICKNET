package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.DTO.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public void insertDataIntoDB(EmployeeDTO employeeDTO){
        String jsonBody ="{\"key\": \"value\"}";
        webClientBuilder.baseUrl("http://192.168.0.9:8098/employees")
                .build().post().uri("/createEmp").bodyValue(employeeDTO).retrieve().toBodilessEntity().block();
    }
}
