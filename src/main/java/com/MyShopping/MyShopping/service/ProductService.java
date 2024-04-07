package com.MyShopping.MyShopping.service;


import com.MyShopping.MyShopping.exceptions.ResourceNotFound;
import com.MyShopping.MyShopping.exceptions.UnAuthorized;
import com.MyShopping.MyShopping.models.AppUser;
import com.MyShopping.MyShopping.models.Product;
import com.MyShopping.MyShopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    UserService userService;
    @Autowired
    ProductRepository productRepository;

    public void registerProduct(Product product, UUID sellerId){
        // First we will check, this userId exists in system or not
        // If exists then what kind of useId it is
        // It it is seller kind of Id then it is fine
        // else, we need to throw exception

        AppUser user = userService.getUserById(sellerId);

        if(user == null){
            throw new ResourceNotFound(String.format("seller with sellerId %s does not exist in system.", sellerId.toString()));
        }

        if(!user.getUserType().equals("SELLER")){
            throw new UnAuthorized(String.format("user with id %s does not have access to perform this operation",sellerId.toString()));
        }

        product.setUser(user);

        productRepository.save(product);
    }
}
