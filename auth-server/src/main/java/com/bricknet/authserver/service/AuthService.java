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
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private static UserProfile userProfile ;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  RedisService redisService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private Notification notificationService;

    private Map<String, String> jwtMap;
    private Map<String, String> otpMap;

    @Autowired
    public AuthService(UserProfile userProfile, PasswordEncoder passwordEncoder, RedisService redisService, JwtService jwtService, Notification notificationService, Map<String, String> jwtMap, Map<String, String> otpMap) {
        this.userProfile = userProfile;
        this.passwordEncoder = passwordEncoder;
        this.redisService = redisService;
        this.jwtService = jwtService;
        this.notificationService = notificationService;
        this.jwtMap = jwtMap;
        this.otpMap = otpMap;
    }

    public AuthService() {

    }

    public static Mono<UserAuthInfo> getUserByUsername(String username) {
        return userProfile.getByUserName(username);
    }

    public static Mono<UserAuthInfo> updatePassword(ForgetPassword forgetPassword) {
        return userProfile.passwordUpdate(forgetPassword);
    }

    public String login(AuthRequest authRequest) {
        UserAuthInfo userAuthInfo = AuthService.getUserByUsername(authRequest.getUsername()).block();
        if(userAuthInfo==null){
            return "Username not found";
        }
        if (userAuthInfo == null || !passwordEncoder.matches(authRequest.getPassword(), userAuthInfo.getPassword())) {

            return "Invalid Password";
        }
        String token= jwtService.generateToken(userAuthInfo);
        try {
//            redisService.set(userAuthInfo.getEmployeeCode(), token);
            jwtMap.put(token,userAuthInfo.getEmployeeCode());
        } catch (Exception e) {

            e.printStackTrace();
        }
        return token;
    }
    public String getOtp(String username){
        AuthService authService=new AuthService();
        UserAuthInfo userAuthInfo = authService.getUserByUsername(username).block();
        String token= jwtService.generateToken(userAuthInfo);
        Random random = new Random();
        String OTP= String.valueOf(100000 + random.nextInt(900000));
        otpMap.put(username,OTP);
        NotificationDto notificationDto=new NotificationDto("This is your OTP  "+OTP,userAuthInfo.getCompanyEmail());
        notificationService.sendEmailNotification(notificationDto);
        return  OTP;
    }

    public String checkOtp(String username,String Otp){
        AuthService authService=new AuthService();

        UserAuthInfo userAuthInfo = authService.getUserByUsername(username).block();
        String OTP= otpMap.get(username);

//     String OTP= redisService.get(username);
        String token= jwtService.generateToken(userAuthInfo);

        if(OTP.equals(Otp)){
            jwtMap.put(userAuthInfo.getEmployeeCode(), token);
            return token;
        }
        jwtMap.remove(username);
        return  "try again";
    }

    public String resetPassword(ForgetPassword forgetPassword){

        UserAuthInfo userAuthInfo=AuthService.updatePassword(forgetPassword).block();
        return "password updated for "+userAuthInfo.getEmployeeCode();
    }
    public String checkJwt(  String jwt ) {
        return jwtMap.get(jwt);
    }
}

 