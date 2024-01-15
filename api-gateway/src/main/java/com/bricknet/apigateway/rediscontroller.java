package com.bricknet.apigateway;

import com.bricknet.apigateway.service.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/redis")
@RestController
public class rediscontroller {
    @Autowired
    private RedisService redisService;

    @PostMapping("/send")
    public ResponseEntity<?> sendToredis(@RequestParam  String key ,  String value)  {
        redisService.set(key,value);
        return new ResponseEntity<>("set", HttpStatusCode.valueOf(200));
    }
    @GetMapping("/get")
    public ResponseEntity<?> getfromRedis(@RequestParam  String key )  {

        return new ResponseEntity<>(redisService.get(key), HttpStatusCode.valueOf(200));
    }

}
