package com.bricknet.authserver.service;

import com.bricknet.authserver.Dto.AuthRequest;
import com.bricknet.authserver.Dto.ForgetPassword;
import com.bricknet.authserver.Dto.NotificationDto;
import com.bricknet.authserver.Dto.UserAuthInfo;
import com.bricknet.authserver.FeignClient.Notification;
import com.bricknet.authserver.FeignClient.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private UserProfile userprofile;
    @Autowired
    private PasswordEncoder passwordEncoder;
   @Autowired
    private  RedisService redisService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private Notification notificationService;



    public String login(AuthRequest authRequest) {
       UserAuthInfo userAuthInfo = (UserAuthInfo) userprofile.getByUserName(authRequest.getUsername()).block();
       if(userAuthInfo==null){
           return "Username not found";
       }
        if (userAuthInfo == null || !passwordEncoder.matches(authRequest.getPassword(), userAuthInfo.getPassword())) {

            return "Invalid Password";
        }
      String token= jwtService.generateToken(userAuthInfo);
//        try {
//            redisService.set(userAuthInfo.getEmployeeCode(), token);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return token;
    }
   public String getOtp(String username){
       UserAuthInfo userAuthInfo = (UserAuthInfo) userprofile.getByUserName(username).block();
       String token= jwtService.generateToken(userAuthInfo);
        Random random = new Random();
        String OTP= String.valueOf(100000 + random.nextInt(900000));
        redisService.set(username,OTP);
        NotificationDto notificationDto=new NotificationDto("This is your OTP  "+OTP,userAuthInfo.getCompanyEmail());
        notificationService.sendEmailNotification(notificationDto);
        return  OTP;
    }

    public String checkOtp(String username,String Otp){
     String OTP= redisService.get(username);
        UserAuthInfo userAuthInfo = (UserAuthInfo) userprofile.getByUserName(username).block();
        String token= jwtService.generateToken(userAuthInfo);

     if(OTP.equals(Otp)){
         redisService.set(userAuthInfo.getEmployeeCode(), token);
return token;
     }
     redisService.remove(username);
     return  "try again";
    }

    public String resetPassword(ForgetPassword forgetPassword){
        UserAuthInfo userAuthInfo=userprofile.passwordUpdate(forgetPassword).block();
        return "password updated for "+userAuthInfo.getEmployeeCode();
    }
}

