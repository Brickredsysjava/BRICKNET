package com.bricknet.authserver.service;
import com.bricknet.authserver.Dto.*;
import com.bricknet.authserver.Exception.LoginException;
import com.bricknet.authserver.FeignClient.Notification;
import com.bricknet.authserver.FeignClient.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private static UserProfile userProfile ;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  RedisService redisService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private TokenCacheService tokenCacheService;

    private static Jedis jedis = new JedisPool("192.168.1.9", 6379).getResource();

//    private Map<String, String> jwtMap;
//    private Map<String, String> otpMap;
//

    @Autowired
    public AuthService(UserProfile userProfile, PasswordEncoder passwordEncoder, RedisService redisService, JwtService jwtService, NotificationService notificationService
//                       Map<String, String> jwtMap, Map<String, String> otpMap
    ) {
        this.userProfile = userProfile;
        this.passwordEncoder = passwordEncoder;
        this.redisService = redisService;
        this.jwtService = jwtService;
        this.notificationService = notificationService;
//        this.jwtMap = jwtMap;
//        this.otpMap = otpMap;
    }

    public AuthService() {

    }

    public static Mono<UserAuthInfo> getUserByUsername(String username) {
        return userProfile.getByUserName(username);
    }

    public static Mono<UserAuthInfo> updatePassword(ForgetPassword forgetPassword) {
        return userProfile.passwordUpdate(forgetPassword);
    }

    public Object login(AuthRequest authRequest) throws LoginException {
        JwtResponse response = new JwtResponse();
        UserAuthInfo userAuthInfo = AuthService.getUserByUsername(authRequest.getUsername()).block();

        if(userAuthInfo==null){
            throw new LoginException("Username not found");
        }
        if (userAuthInfo == null || !passwordEncoder.matches(authRequest.getPassword(), userAuthInfo.getPassword())) {

            throw new LoginException( "Invalid Password");
        }
        String token= jwtService.generateToken(userAuthInfo);
        try {
            TokenCacheService.storeToken(userAuthInfo.getEmployeeCode(), token);
            //redisService.set(userAuthInfo.getEmployeeCode(), token);
            jedis.set(userAuthInfo.getEmployeeCode(),token);
        } catch (Exception e) {

            e.printStackTrace();
        }
        response.setRole(userAuthInfo.getRole());
        response.setUsername(userAuthInfo.getEmployeeName());
        response.setUserId(userAuthInfo.getUuid());
        response.setJwtTokens(token);
        response.setEmpCode(userAuthInfo.getEmployeeCode());
        response.setEmailId(userAuthInfo.getCompanyEmail());
        return response;
    }
    public void getOtp(String username) throws Exception {
        AuthService authService=new AuthService();
        UserAuthInfo userAuthInfo = authService.getUserByUsername(username).block();
        String token= jwtService.generateToken(userAuthInfo);
        Random random = new Random();
        String OTP= String.valueOf(100000 + random.nextInt(900000));
        jedis.set(username,OTP);
        OtpService.sendOTPEmail(userAuthInfo.getCompanyEmail(), OTP);
    }

    public String checkOtp(String username,String Otp){
        AuthService authService=new AuthService();

        UserAuthInfo userAuthInfo = authService.getUserByUsername(username).block();
        String OTP= jedis.get(username);

//     String OTP= redisService.get(username);
        String token= jwtService.generateToken(userAuthInfo);

        if(OTP.equals(Otp)){
            jedis.set(userAuthInfo.getEmployeeCode(), token);
            return token;
        }
        jedis.del(username);
        return  "try again";
    }

    public String resetPassword(ForgetPassword forgetPassword){

        UserAuthInfo userAuthInfo=AuthService.updatePassword(forgetPassword).block();
        return "password updated for "+userAuthInfo.getEmployeeCode();
    }
    public String checkJwt(  String empcode ) {
        //return jwtMap.get(empcode);
        //return redisService.get(empcode);
        return TokenCacheService.getToken(empcode);
    }
}

 