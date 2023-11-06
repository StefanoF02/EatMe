package com.example.EatMe.service;

import com.example.EatMe.model.Customer;
import com.example.EatMe.model.User;
import com.example.EatMe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer setEmail(@RequestParam(value = "id") int id,@RequestParam(value = "mail") String mail){
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

    public HttpStatus setPassword(@RequestParam(value = "id") int id, @RequestParam(value = "oldPassword") String oldPassword, @RequestParam(value = "newPassword") String newPassword){
        Optional<Customer> customerToEdit = customerRepository.findById(id);
        if(customerToEdit.isPresent()){
            if(customerToEdit.get().getPassword().equals(oldPassword)) {
                customerToEdit.get().setPassword(newPassword);
                User userToSave = customerRepository.save(customerToEdit.get());
                return HttpStatus.OK;
            }else{
                return HttpStatus.UNAUTHORIZED;
            }
        }else{
            return HttpStatus.NOT_FOUND;
        }
    }

}
