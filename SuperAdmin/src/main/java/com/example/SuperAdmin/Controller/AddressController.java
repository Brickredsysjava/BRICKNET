package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.DTO.AddressDTO;
import com.example.SuperAdmin.Entity.Address;
import com.example.SuperAdmin.Repository.CustomQuery;
import com.example.SuperAdmin.Service.AddressService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("*")
@Validated
@RestController
@RequestMapping("/user/address")
public class AddressController {
    @Autowired
    private AddressService service;

    @Autowired
    private ModelMapper modelMapper;

    private CustomQuery customQuery;

    public AddressController(CustomQuery customQuery) {
        this.customQuery = customQuery;
    }
    @PostMapping("/addAddress")
    public ResponseEntity<Address> addAddress(@RequestBody @Valid AddressDTO addressDTO) {
        Address address = modelMapper.map(addressDTO, Address.class);
        Address savedAddress = service.saveAddress(address);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    @PostMapping("/addListOfAddress")
    public ResponseEntity<List<Address>> addAddress(@RequestBody @Valid List<AddressDTO> addressDTOList) {
        List<Address> addresses = addressDTOList.stream()
                .map(dto -> modelMapper.map(dto, Address.class))
                .collect(Collectors.toList());

        List<Address> savedAddresses = service.saveAddressList(addresses);
        return new ResponseEntity<>(savedAddresses, HttpStatus.CREATED);
    }

    @GetMapping("/allAddress")
    public ResponseEntity<List<AddressDTO>> findAllAddress() {
        List<AddressDTO> allAddresses = service.getAddress();
        if (allAddresses != null) {
            return new ResponseEntity<>(allAddresses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/AddressByEmployeeCode")
    public ResponseEntity<List<AddressDTO>> findAddressByEmployeeCode(@RequestParam("employeeCode") String employeeCode) {
        List<AddressDTO> address = service.getAddressByEmployeeCode(employeeCode);
        if (address != null) {
            return new ResponseEntity<>(address, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateAddress")
    public ResponseEntity<Address> updateAddressById(@RequestParam("employeeCode") String employeeCode, @RequestParam("typeOfAddress") String typeOfAddress, @RequestBody @Valid AddressDTO addressDTO) {
        Address address = modelMapper.map(addressDTO, Address.class);
        String addressId = customQuery.getAddressIdFromEmpCode(employeeCode , typeOfAddress);
        if(addressId.equals("Data Not Found")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Address updatedAddress = service.updateAddressById(addressId,    address);
        if (updatedAddress != null) {
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/deleteAddress")
    public ResponseEntity<String> deleteAddress(@RequestParam("employeeCode") String employeeCode) {
        String result = service.deleteAddress(employeeCode);
        if ("Deleted".equals(result)) {
            return new ResponseEntity<>("Address with employeeCode " + employeeCode + " has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Address with employeeCode " + employeeCode + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/test")
    public String test(){
        return "Super Admin is up and running";
    }
}
