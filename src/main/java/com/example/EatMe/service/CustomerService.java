package com.example.EatMe.service;

import com.example.EatMe.dto.CustomerAddressDTO;
import com.example.EatMe.model.Customer;
import com.example.EatMe.repository.AddressRepository;
import com.example.EatMe.repository.CustomerRepository;
import com.example.EatMe.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderRepository orderRepository;


    public CustomerAddressDTO createUserAddressDTO(CustomerAddressDTO customerAddressDTO){
        //# This function creates an address even if the customer is already existing?
        var customerInDB = customerRepository.findByMail(customerAddressDTO.getCustomer().getMail());
        if(customerInDB.isPresent()){
            return null;
        }else{
            var saveInDB = addressRepository.save(customerAddressDTO.getAddress());
            customerAddressDTO.getCustomer().setAddress(saveInDB);
            customerAddressDTO.getCustomer().setUuid(UUID.randomUUID().toString());
            customerRepository.save(customerAddressDTO.getCustomer());
            return customerAddressDTO;
        }
    }

    public Customer setEmail(String uuid, String mail){
        Optional<Customer> customerToEdit = customerRepository.findByUUID(uuid);
            if(customerToEdit.isPresent()){
                //Comparing old email to new email
                customerToEdit.get().setMail(mail);
                Customer customerToSave = customerRepository.save(customerToEdit.get());
                return customerToSave;
            }else{
                return null;
            }
    }

    public HttpStatus setPassword(String uuid, String oldPassword, String newPassword){
        Optional<Customer> customerToEdit = customerRepository.findByUUID(uuid);
        if(customerToEdit.isPresent()){
            if(customerToEdit.get().getPassword().equals(oldPassword)) {
                customerToEdit.get().setPassword(newPassword);
                Customer customerToSave = customerRepository.save(customerToEdit.get());
                return HttpStatus.OK;
            }else{
                return HttpStatus.UNAUTHORIZED;
            }
        }else{
            return HttpStatus.NOT_FOUND;
        }
    }

    public boolean setName(String uuid, String newName, String newSurname){
        Optional<Customer> customerToEdit = customerRepository.findByUUID(uuid);
        if(customerToEdit.isPresent()){
            customerToEdit.get().setFirstname(newName);
            customerToEdit.get().setSurname(newSurname);
            Customer customerToSave = customerRepository.save(customerToEdit.get());
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteUserAddressDTO(String uuid){
        var customerToDelete = customerRepository.findByUUID(uuid);
        if(customerToDelete.isPresent()){
            var addressIDToDelete = customerToDelete.get().getAddress().getId();
            addressRepository.deleteById(addressIDToDelete);
            int customerID = customerToDelete.get().getId();
            customerRepository.deleteById(customerID);
            return true;
        }else {
            return false;
        }
    }

}
