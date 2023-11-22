package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.DTO.*;
import com.example.SuperAdmin.Service.EmployeeService;
import com.example.SuperAdmin.auth.UserCredential;
import com.example.SuperAdmin.Entity.Profile;
import com.example.SuperAdmin.Repository.CustomQuery;
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
    private EmployeeService employeeService;
    @Autowired
    private ModelMapper modelMapper;

    private CustomQuery customQuery;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ProfileController(CustomQuery customQuery) {
        this.customQuery = customQuery;
    }

    @PostMapping("/addProfile")
    public ResponseEntity<Profile> addProfile(@RequestBody @Valid ProfileDTO profileDTO) throws ServiceNotFoundException {
        NotificationDTO notificationDTO = new NotificationDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        String message =
                "You are created  \n" + "\n"
                        + "UserId:" + profileDTO.getEmployeeCode() + "\n"
                        + "Password: " + profileDTO.getPassword() +"\n" +
                        "\n";

        notificationDTO.setMessage(message);
        notificationDTO.setRecipient(profileDTO.getPersonalEmail());
        notificationDTO.setTimeStamp(LocalDateTime.now());



        notificationService.pushNotification(notificationDTO);
        Profile profile = modelMapper.map(profileDTO, Profile.class);


        //profile.setPassword(passwordEncoder.encode(profileDTO.getPassword()));
        Profile savedProfile = profileService.saveProfile(profile);
        employeeDTO.setAuto_id(savedProfile.getId());
        employeeDTO.setPassword(profileDTO.getPassword());
        employeeDTO.setName(profileDTO.getFirstName() + " " + profileDTO.getLastName());
        employeeDTO.setEmail(profileDTO.getCompanyEmail());
        employeeDTO.setEmp_id(savedProfile.getEmployeeCode());
        employeeService.insertDataIntoDB(employeeDTO);
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

    @GetMapping("/ProfileByEmployeeCode")
    public ResponseEntity<ProfileDTO> findProfileById(@RequestParam("employeeCode") String employeeCode) {
        ProfileDTO profile = profileService.getProfileByEmployeeCode(employeeCode);
        if (profile != null) {
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/updateProfile")
    public ResponseEntity<Profile> updateProfileById(@RequestParam("employeeCode") String employeeCode,  @RequestBody @Valid ProfileDTO profileDTO) {
        String profile_id ="";
        if (employeeCode != null && !employeeCode.isEmpty()) {
            profile_id = customQuery.getProfileIDFromEmpCode(employeeCode);
            if(profile_id.equals("Data not Found")){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            Profile profile = modelMapper.map(profileDTO, Profile.class);
            if (!profile_id.equals("")) {
                Profile updatedProfile = profileService.updateProfileById(profile_id, profile);
                return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        else {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
}

    @GetMapping("/timeLine")
    public ResponseEntity<TimeLine> getProfileInfo(@RequestParam("employeeCode") String employeeCode) {
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

    @GetMapping("/getEmailByEmployeeCode")
    public ResponseEntity<String> getEmailByEmployeeCode(@RequestParam("employeeCode") String employeeCode) {
        String email = profileService.getEmailByEmployeeCode(employeeCode);
        if (email != null) {
            return ResponseEntity.ok(email);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllEmail")
    public ResponseEntity<List<String>> getAllEmail() {
        return ResponseEntity.ok(customQuery.getAllEmails());
    }

}

