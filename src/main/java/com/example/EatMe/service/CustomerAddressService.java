package com.example.EatMe.service;

import com.example.EatMe.dto.CustomerAddressDTO;
import com.example.EatMe.model.Address;
import com.example.EatMe.repository.AddressRepository;
import com.example.EatMe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAddressService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;

    public CustomerAddressDTO createUserAddressDTO(CustomerAddressDTO customerAddressDTO){
        var customerInDB = customerRepository.findByMail(customerAddressDTO.getCustomer().getMail());
        if(customerInDB.isPresent()){
            return null;
        }else{
            var saveInDB = addressRepository.save(customerAddressDTO.getAddress());
            customerAddressDTO.getCustomer().setAddress(saveInDB);
            customerRepository.save(customerAddressDTO.getCustomer());
            return customerAddressDTO;
        }
    }

    public Address editAddress(int id, Address updatedAddress){
        var customerInDB = customerRepository.findById(id);
        if(customerInDB.isPresent()){
            var addressId = customerInDB.get().getAddress().getId();
            addressRepository.save(updatedAddress);
            customerInDB.get().setAddress(updatedAddress);
            addressRepository.deleteById(addressId);
            return updatedAddress;
        }
        return null;
    }

    public Address getAddress(int id){
        var customerInDB = customerRepository.findById(id);
        if(customerInDB.isPresent()){
        Address returningAddress = customerInDB.get().getAddress();
        return returningAddress;
        }else{
            return null;
        }
    }

    public boolean deleteUserAddressDTO(int id){
        var customerToDelete = customerRepository.findById(id);
        if(customerToDelete.isPresent()){
            var addressIDToDelete = customerToDelete.get().getAddress().getId();
            addressRepository.deleteById(addressIDToDelete);
            customerRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
