package com.bricknet.authserver.FeignClient;

import com.bricknet.authserver.Dto.ForgetPassword;
import com.bricknet.authserver.Dto.UserAuthInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("super-admin/user")
public interface Userprofile {

    @GetMapping("/profileFromUserName")
    public ResponseEntity<UserAuthInfo> getByUserName(@RequestParam String username) ;
    @PostMapping("/passwordUpdate")
    public ResponseEntity<UserAuthInfo>passwordUpdate(@RequestBody ForgetPassword forgetPassword);
}
