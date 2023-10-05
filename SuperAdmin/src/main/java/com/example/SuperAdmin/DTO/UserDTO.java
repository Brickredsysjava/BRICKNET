package com.example.SuperAdmin.DTO;

import com.example.SuperAdmin.Entity.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private ProfileDTO profileDTO;
    private PersonalDetailsDTO personalDetailsDTO;
    private List<EducationDTO> educationDTOList;
    private BankDetailsDTO bankDetailsDTO;
    private List<AddressDTO> addressDTOList;

}
