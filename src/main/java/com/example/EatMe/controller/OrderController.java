package com.example.EatMe.controller;

import com.example.EatMe.model.Order;
import com.example.EatMe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{vendorUUID}/createOrder/{customerUUID}")
    public ResponseEntity<Order> createOrder(@PathVariable String customerUUID, @PathVariable String vendorUUID,  @RequestBody Order newOrder){
        Order savedOrder = orderService.createOrder(customerUUID,vendorUUID, newOrder);
        if(savedOrder != null){
            return new ResponseEntity<Order>(savedOrder, HttpStatus.OK);
        }else{
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("/cancel/{orderKey}")
    public ResponseEntity cancelOrder(@PathVariable String orderKey){
        orderService.cancelOrder(orderKey);
        return new ResponseEntity("Order got canceled", HttpStatus.OK);
    }
}
