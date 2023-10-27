package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.DTO.NotificationDTO;
import com.example.SuperAdmin.DTO.ProfileDTO;
import com.example.SuperAdmin.DTO.ResetPassword;
import com.example.SuperAdmin.auth.UserCredential;
import com.example.SuperAdmin.Entity.Profile;
import com.example.SuperAdmin.DTO.TimeLine;
import com.example.SuperAdmin.Service.NotificationService;
import com.example.SuperAdmin.Service.ProfileService;
import com.example.SuperAdmin.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.management.ServiceNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/user/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/addProfile")
    public ResponseEntity<Profile> addProfile(@RequestBody @Valid ProfileDTO profileDTO) throws ServiceNotFoundException {
        NotificationDTO notificationDTO = new NotificationDTO();
        String message =
                "You are created  \n" + "\n"
                        + "UserId " + profileDTO.getEmployeeCode() + "\n"
                        + "FROM: " + profileDTO.getPassword() +"\n" +
                        "\n";

        notificationDTO.setMessage(message);
        notificationDTO.setRecipient(profileDTO.getPersonalEmail());
        notificationDTO.setTimeStamp(LocalDateTime.now());


        notificationService.pushNotification(notificationDTO);
        Profile profile = modelMapper.map(profileDTO, Profile.class);

        //profile.setPassword(passwordEncoder.encode(profileDTO.getPassword()));
        Profile savedProfile = profileService.saveProfile(profile);
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    @PostMapping("/addListOfProfile")
    public ResponseEntity<List<Profile>> addProfile(@RequestBody @Valid List<ProfileDTO> profileDTOList) {
        List<Profile> profiles = profileDTOList.stream()
                .map(profileDTO -> modelMapper.map(profileDTO, Profile.class))
                .collect(Collectors.toList());
        List<Profile> savedProfiles = profileService.saveProfile(profiles);
        return new ResponseEntity<>(savedProfiles, HttpStatus.CREATED);
    }

    @GetMapping("/allProfile")
    public ResponseEntity<List<ProfileDTO>> findAllProfile() {
        List<ProfileDTO> allProfiles = profileService.getProfile();
        if (allProfiles != null) {
            return new ResponseEntity<>(allProfiles, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/ProfileById/{id}")
    public ResponseEntity<ProfileDTO> findProfileById(@PathVariable String id) {
        ProfileDTO profile = profileService.getProfileById(id);
        if (profile != null) {
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/updateProfile/{id}")
    public ResponseEntity<Profile> updateProfileById(@PathVariable String id, @RequestBody @Valid ProfileDTO profileDTO) {
    if (id != null && !id.isEmpty()) {
        Profile profile = modelMapper.map(profileDTO, Profile.class);
        Profile updatedProfile = profileService.updateProfileById(id, profile);
        if (updatedProfile != null) {
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } else {
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }
}

    @GetMapping("/{employeeCode}/timeLine")
    public ResponseEntity<TimeLine> getProfileInfo(@PathVariable String employeeCode) {
        TimeLine timeLine = profileService.getTimelineByEmployeeCode(employeeCode);
        if (timeLine != null) {
            return new ResponseEntity<>(timeLine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/fullName")
    public ResponseEntity<String> getFullNameByEmployeeCode(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String fullName = profileService.getFullNameByEmployeeCode(JwtUtil.extractClaim(authorizationHeader,"employeeCode"));

        if (fullName != null) {
            return ResponseEntity.ok(fullName);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/profileFromUserName")
    public ResponseEntity<UserCredential>getByUserName(@RequestParam String username){
   UserCredential userCredential=   profileService.getByUserName(username);
   return new ResponseEntity<>(userCredential,HttpStatus.OK);
    }
    @PostMapping("/passwordUpdate")
    public ResponseEntity<UserCredential> passwordUpdate(@RequestBody ResetPassword resetPassword){
   UserCredential userCredential=profileService.updatePassword(resetPassword);
        return new ResponseEntity<>(userCredential,HttpStatus.OK);
    }

    }

