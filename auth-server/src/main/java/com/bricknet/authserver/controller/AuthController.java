package com.bricknet.authserver.controller;

import com.bricknet.authserver.Dto.AuthRequest;
import com.bricknet.authserver.Dto.ForgetPassword;
import com.bricknet.authserver.Dto.NotificationDto;
import com.bricknet.authserver.FeignClient.Notification;
import com.bricknet.authserver.service.AuthService;
import com.bricknet.authserver.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final JwtService jwtService;

    @Autowired
    private Notification notification;

    @PostMapping(value = "/login")
    public ResponseEntity<String>login(@RequestBody AuthRequest authRequest) {

        return  new ResponseEntity<>(authService.login(authRequest), HttpStatus.OK);
    }
    @GetMapping(value = "/getOtp")
    public ResponseEntity<String>getOtp(@RequestParam String username ) throws Exception {
        String otp = authService.getOtp(username);
        return new ResponseEntity<>(otp,HttpStatus.OK);
    }
    @GetMapping(value = "/checkOtp")
    public ResponseEntity<String>checkOtp(@RequestParam String username,String otp ) {
        return new ResponseEntity<>(authService.checkOtp(username,otp),HttpStatus.OK);
    }
    @PostMapping(value = "/reset")
    public ResponseEntity<String>resetPassword(@RequestBody ForgetPassword forgetPassword) {

            return  new ResponseEntity<>(authService.resetPassword(forgetPassword), HttpStatus.OK);

    }

    @GetMapping("/test")
    public String getTest(){
        return  "Auth server is up and running";
    }


}
