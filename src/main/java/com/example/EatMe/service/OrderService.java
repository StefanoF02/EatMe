package com.example.EatMe.service;

import com.example.EatMe.model.Customer;
import com.example.EatMe.model.Order;
import com.example.EatMe.repository.CustomerRepository;
import com.example.EatMe.repository.OrderRepository;
import com.example.EatMe.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VendorRepository vendorRepository;

    public Order createOrder(int customerId, Order newOrder){
        Optional<Order> orderInDB = orderRepository.findByOrderKey(newOrder.getOrderKey());
        if(orderInDB.isPresent()){
            return null;
        }else{
            Customer customer = customerRepository.findById(customerId).get();
            newOrder.setCustomer(customer);
            Order orderToSave = orderRepository.save(newOrder);
            customer.getMadeOrders().add(orderToSave);
            customerRepository.save(customer);
            return orderToSave;
        }
    }
}
