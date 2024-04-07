package com.MyShopping.MyShopping.controller;


import com.MyShopping.MyShopping.exceptions.ResourceNotFound;
import com.MyShopping.MyShopping.exceptions.UnAuthorized;
import com.MyShopping.MyShopping.models.Product;
import com.MyShopping.MyShopping.repository.ProductRepository;
import com.MyShopping.MyShopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

@RestController
@RequestMapping("/seller")
public class SellerController  {

    @Autowired
    ProductService productService;

    @PostMapping("/product/add")

    public String addProduct(@RequestBody Product product, @RequestParam UUID sellerId){

        try{
            productService.registerProduct(product, sellerId);
        }catch (ResourceNotFound resourceNotFound){

            return resourceNotFound.getMessage();
        }catch (UnAuthorized unAuthorized){

            return unAuthorized.getMessage();
        }

        return "Product saved successfully.";
    }
}
