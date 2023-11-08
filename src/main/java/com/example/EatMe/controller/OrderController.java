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

    @PostMapping("/{customerId}/createOrder")
    public ResponseEntity<Order> createOrder(@PathVariable int customerId, @RequestBody Order newOrder){
        Order savedOrder = orderService.createOrder(customerId, newOrder);
        return new ResponseEntity<Order>(savedOrder, HttpStatus.OK);
//        if (orderService.createOrder(customerId,newOrder) == true){
//            return new ResponseEntity("Order created", HttpStatus.OK);
//        }else{
//            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
//
//        }
    }
}
