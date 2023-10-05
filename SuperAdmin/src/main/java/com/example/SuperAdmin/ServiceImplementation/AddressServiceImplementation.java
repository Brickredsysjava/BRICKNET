package com.example.SuperAdmin.ServiceImplementation;

import com.example.SuperAdmin.DTO.AddressDTO;
import com.example.SuperAdmin.Entity.Address;
import com.example.SuperAdmin.Repository.AddressRepository;
import com.example.SuperAdmin.Service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImplementation implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> saveAddressList(List<Address> address) {
        return addressRepository.saveAll(address);
    }


    @Override
    public List<AddressDTO> getAddress() {
        List<Address> addressList = addressRepository.findAll();
        return addressList.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO getAddressById(String id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            return modelMapper.map(address, AddressDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public String deleteAddress(String id) {
        addressRepository.deleteById(id);
        return "Deleted" + id;
    }

    @Override
    public Address updateAddressById(String id, Address address) {
        Address existingAddress = addressRepository.findById(id).orElse(null);

        if (existingAddress != null) {
            existingAddress.setTypeOfAddress(address.getTypeOfAddress());
            existingAddress.setStreetAddress(address.getStreetAddress());
            existingAddress.setApartment(address.getApartment());
            existingAddress.setCity(address.getCity());
            existingAddress.setCountry(address.getCountry());
            existingAddress.setDistrict(address.getDistrict());
            existingAddress.setState(address.getState());
            existingAddress.setPincode(address.getPincode());


            return addressRepository.save(existingAddress);
        } else {
            return null;
        }
    }


}
