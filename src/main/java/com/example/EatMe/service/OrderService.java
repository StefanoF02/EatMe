package com.example.EatMe.service;

import com.example.EatMe.model.Customer;
import com.example.EatMe.model.Order;
import com.example.EatMe.model.Vendor;
import com.example.EatMe.repository.CustomerRepository;
import com.example.EatMe.repository.OrderRepository;
import com.example.EatMe.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VendorRepository vendorRepository;

    public Order createOrder(String customerUUID, String VendorUUID, Order newOrder){
        Optional<Order> orderInDB = orderRepository.findByOrderKey(newOrder.getOrderKey());
        if(orderInDB.isPresent()){
            return null;
        }else{
            Customer customer = customerRepository.findByUUID(customerUUID).get();
            Vendor vendor = vendorRepository.findByUUID(VendorUUID).get();
            newOrder.setCustomer(customer);
            newOrder.setVendor(vendor);
            newOrder.setOrderKey(UUID.randomUUID().toString().replace("-", ""));
            Order orderToSave = orderRepository.save(newOrder);
            customer.getMadeOrders().add(orderToSave);
            customerRepository.save(customer);
            return orderToSave;
        }
    }

    public Order cancelOrder(String orderKey){
        Optional<Order> orderInDB = orderRepository.findByOrderKey(orderKey);
        if(orderInDB.isPresent()){
            orderInDB.get().setOrderStatus("canceled");
            Order updatedOrder = orderRepository.save(orderInDB.get());
            return updatedOrder;
        }else{
            return null;
        }
    }
}
