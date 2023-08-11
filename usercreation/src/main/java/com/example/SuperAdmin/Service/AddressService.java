package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.Entity.Address;

import java.util.List;

public interface AddressService {


    Address saveAddress(Address address);

    List<Address> saveAddressList(List<Address> address);

    List<Address> getAddress();

    Address getAddressById(Long id);

    //    public User getEmployeeByName(String name) {
//        return userRepository.findByName(name);
//    }
    String deleteAddress(Long id);

    Address updateAddress(Address address);
}
