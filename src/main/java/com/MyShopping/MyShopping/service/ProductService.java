package com.MyShopping.MyShopping.service;


import com.MyShopping.MyShopping.dto.ProductDTO;
import com.MyShopping.MyShopping.exceptions.ResourceNotFound;
import com.MyShopping.MyShopping.exceptions.UnAuthorized;
import com.MyShopping.MyShopping.models.AppUser;
import com.MyShopping.MyShopping.models.Product;
import com.MyShopping.MyShopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    UserService userService;
    @Autowired
    ProductRepository productRepository;

    public Product getProductById(UUID id){
        return productRepository.findById(id).orElse(null);
    }

    public void updateProductQuantity(UUID productID, int quantity){
        productRepository.updateProductQuantity(productID, quantity);
    }

    public List<ProductDTO> convertProductToProductDTO(List<Product> products){

        List<ProductDTO> productList = new ArrayList<>();

        for(Product product : products){

            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductName(product.getName());
            productDTO.setProductCategory(product.getCategory());
            productDTO.setId(product.getId());
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());
            productDTO.setRating(product.getRating());
            productDTO.setSellerName(product.getUser().getName());
            productList.add(productDTO);
        }

        return productList;
    }

    public List<ProductDTO> searchByProductName(String productName){

        List<Product> products = productRepository.getProductByName(productName);
        List<ProductDTO> productList = convertProductToProductDTO(products);
        return productList;
    }

    public List<ProductDTO> searchByCategory(String category){
        List<Product> products = productRepository.getProductByCategory(category);
        List<ProductDTO> productList = convertProductToProductDTO(products);
        return productList;
    }

    public List<ProductDTO> searchByCategoryAndProductName(String productName, String category){
        List<Product> products = productRepository.getProductByCategoryAndName(category, productName);
        List<ProductDTO> productList = convertProductToProductDTO(products);
        return productList;
    }

    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productList = convertProductToProductDTO(products);
        return productList;
    }

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
