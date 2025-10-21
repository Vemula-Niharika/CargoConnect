package com.alpha.abclogistics.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.abclogistics.Dto.OrderDto;
import com.alpha.abclogistics.Dto.ResponseStructure;
import com.alpha.abclogistics.Entity.Order;
import com.alpha.abclogistics.Service.OrderService;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeuserorder")
    public ResponseEntity<ResponseStructure<Order>> placeuserorder(@RequestBody OrderDto orderDto) {
        return orderService.placeuserorder(orderDto);
        
        
        
    }
}
