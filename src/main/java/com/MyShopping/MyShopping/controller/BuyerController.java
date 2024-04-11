package com.MyShopping.MyShopping.controller;


import com.MyShopping.MyShopping.dto.BillDTO;
import com.MyShopping.MyShopping.dto.OrderDetailsDTO;
import com.MyShopping.MyShopping.models.OrderTable;
import com.MyShopping.MyShopping.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    BuyerService buyerService;

    @PostMapping("/placeorder")
    public BillDTO placeOrder(@RequestBody List<OrderDetailsDTO> orders, @RequestParam UUID userId){
        return buyerService.placeOrder(orders, userId);
    }
}
