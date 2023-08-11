package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.Entity.Address;
import com.example.SuperAdmin.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
    private AddressService service;

    @PostMapping("/addAddress")
    public Address addAddress(@RequestBody Address address) {
        return service.saveAddress(address);
    }

    @PostMapping("/addListOfAddress")
    public List<Address> addAddress(@RequestBody List<Address> addresses) {
        return service.saveAddressList(addresses);
    }

    @GetMapping("/allAddress")
    public List<Address> findAllAddress() {
        return service.getAddress();
    }

    @GetMapping("/AddressById/{id}")
    public Address findAddressById(@PathVariable Long id) {
        return service.getAddressById(id);
    }

    @PutMapping("/updateAddress")
    public Address updateAddress(@RequestBody Address address) {
        return service.updateAddress(address);
    }

    @DeleteMapping("/deleteAddress/{id}")
    public String deleteAddress(@PathVariable Long id) {
        return service.deleteAddress(id);
    }
}
