package com.ms.order_service.controllers;

import com.ms.order_service.s2s.CartResponse;
import com.ms.order_service.s2s.CartService;
import com.ms.order_service.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orders")
@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Object create(){
       return orderService.create("1");
    }
}
