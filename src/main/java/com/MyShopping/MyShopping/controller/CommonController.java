package com.MyShopping.MyShopping.controller;


import com.MyShopping.MyShopping.dto.ProductDTO;
import com.MyShopping.MyShopping.models.AppUser;
import com.MyShopping.MyShopping.models.Product;
import com.MyShopping.MyShopping.service.ProductService;
import com.MyShopping.MyShopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // if any request starting with an /api that means all those requests are going to come at common controller

public class CommonController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @PostMapping("/user/register")

    public String registerUser(@RequestBody AppUser appUser){

        userService.registerUser(appUser);
        return "User saved successfully";
    }

    @GetMapping("/product/search")
    public List<ProductDTO> searchProductByCategory(@RequestParam (required = false) String category, @RequestParam(required = false) String productName){
        if(category != null && productName != null){

            return productService.searchByCategoryAndProductName(category, productName);
        }else if(category != null){
            return productService.searchByCategory(category);

        }else if(productName != null){

            return productService.searchByProductName(productName);
        }else{

            return productService.getAllProducts();
        }
    }
}
