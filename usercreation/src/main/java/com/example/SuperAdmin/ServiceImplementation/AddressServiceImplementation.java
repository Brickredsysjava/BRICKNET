package com.example.SuperAdmin.ServiceImplementation;

import com.example.SuperAdmin.Entity.Address;
import com.example.SuperAdmin.Repository.AddressRepository;
import com.example.SuperAdmin.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImplementation implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> saveAddressList(List<Address> address) {
        return addressRepository.saveAll(address);
    }

   @Override
    public List<Address> getAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteAddress(Long id) {
        addressRepository.deleteById(id);
        return "Address removed !! " + id;
    }

    @Override
    public Address updateAddress(Address address) {
        Address existingAddress = addressRepository.findById(address.getId()).orElse(null);

        return addressRepository.save(existingAddress);
    }

}
