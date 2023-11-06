package com.example.EatMe.service;

import com.example.EatMe.model.Customer;
import com.example.EatMe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer setEmail(int id, String mail){
        Optional<Customer> customerToEdit = customerRepository.findById(id);
            if(customerToEdit.isPresent()){
                //Comparing old email to new email
                customerToEdit.get().setMail(mail);
                Customer customerToSave = customerRepository.save(customerToEdit.get());
                return customerToSave;
            }else{
                return null;
            }
    }

    public HttpStatus setPassword(int id, String oldPassword, String newPassword){
        Optional<Customer> customerToEdit = customerRepository.findById(id);
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

    public boolean setName(int id, String newName, String newSurname){
        Optional<Customer> customerToEdit = customerRepository.findById(id);
        if(customerToEdit.isPresent()){
            customerToEdit.get().setFirstname(newName);
            customerToEdit.get().setSurname(newSurname);
            Customer customerToSave = customerRepository.save(customerToEdit.get());
            return true;
        }else{
            return false;
        }
    }

}
