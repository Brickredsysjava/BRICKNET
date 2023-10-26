package com.bricknet.authserver.controller;

import com.bricknet.authserver.Dto.AuthRequest;
import com.bricknet.authserver.Dto.ForgetPassword;
import com.bricknet.authserver.Dto.JwtResponse;
import com.bricknet.authserver.Exception.LoginException;
import com.bricknet.authserver.service.AuthService;
import com.bricknet.authserver.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final JwtService jwtService;

    @PostMapping(value = "/login")
    public ResponseEntity<?>login(@RequestBody AuthRequest authRequest) {

        try {
            return new ResponseEntity<>(authService.login(authRequest), HttpStatus.OK);
        }catch (LoginException l)
        {
            return new ResponseEntity<>(l.getMessage(), HttpStatusCode.valueOf(401));
        }
    }
    @GetMapping(value = "/getOtp")
    public ResponseEntity<?>getOtp(@RequestParam String username ) throws Exception {
        authService.getOtp(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "/checkOtp")
    public ResponseEntity<String>checkOtp(HttpServletRequest request, @RequestParam String username, String otp ) {


        return new ResponseEntity<>(authService.checkOtp(username,otp),HttpStatus.OK);
    }
    @PostMapping(value = "/reset")
    public ResponseEntity<String>resetPassword(HttpServletRequest request,@RequestBody ForgetPassword forgetPassword) {
        String authorizationHeader = request.getHeader("Authorization");
        String username =jwtService.extractClaim(authorizationHeader,"employeeCode");
        if(username.equals(forgetPassword.getUsername())) {
            return new ResponseEntity<>(authService.resetPassword(forgetPassword), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("",HttpStatusCode.valueOf(401));
        }
    }

    @GetMapping("/test")
    public String getTest(){
        return  "Auth server is up and running";
    }

}
