package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.DTO.AddressDTO;
import com.example.SuperAdmin.Entity.Address;

import java.util.List;
import java.util.UUID;

public interface AddressService {


    Address saveAddress(Address address);

    List<Address> saveAddressList(List<Address> address);

    List<AddressDTO> getAddress();

    AddressDTO getAddressById(String id);


    String deleteAddress(String id );

    Address updateAddressById(String id,Address address);
}
