package com.example.EatMe.service;

import com.example.EatMe.model.Order;
import com.example.EatMe.repository.CustomerRepository;
import com.example.EatMe.repository.OrderRepository;
import com.example.EatMe.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VendorRepository vendorRepository;

    public boolean createOrder(int customerID, Order newOrder){
        var customer = customerRepository.findById(customerID);
        if(customerRepository.findById(customerID).isPresent()){
            //getCustomers.add(customer.get()) == null doesnt work atm.
            newOrder.getCustomers().add(customer.get());
            var orderToSave = orderRepository.save(newOrder);
            return true;
        }else {
            return false;
        }
    }
}
