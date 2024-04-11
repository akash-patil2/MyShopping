package com.MyShopping.MyShopping.service;


import com.MyShopping.MyShopping.dto.BillDTO;
import com.MyShopping.MyShopping.dto.OrderDetailsDTO;
import com.MyShopping.MyShopping.dto.OrderProductDTO;
import com.MyShopping.MyShopping.models.AppUser;
import com.MyShopping.MyShopping.models.OrderTable;
import com.MyShopping.MyShopping.models.Product;
import com.MyShopping.MyShopping.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BuyerService {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderRepository orderRepository;

    public BillDTO placeOrder(List<OrderDetailsDTO> OrderDetailsDTOList, UUID userID){

        AppUser user = userService.getUserById(userID);
        OrderTable orderTable = new OrderTable();
        BillDTO bill = new BillDTO();

        int totalPrice =0;
        int totalQuantity =0;

        List<Product> products = new ArrayList<>();
        List<OrderProductDTO> orderProducts = new ArrayList<>();
        for(OrderDetailsDTO order : OrderDetailsDTOList){
            OrderProductDTO orderProduct = new OrderProductDTO();
            UUID productId = order.getId();
            orderProduct.setProductID(productId);

            totalQuantity += order.getQuantity();
            Product product = productService.getProductById(productId);
            orderProduct.setProductName(product.getName());
            orderProduct.setQuantity(order.getQuantity());

            totalPrice += (order.getQuantity()*product.getPrice());
            products.add(product);
            orderTable.setPaymentMode(order.getPaymentMode());
            orderProducts.add(orderProduct);
            int updatedQuantity = product.getQuantity() - order.getQuantity();
            productService.updateProductQuantity(productId, updatedQuantity);
        }

        bill.setBuyerID(user.getId());
        bill.setBuyerName(user.getName());
        bill.setTotalPrice(totalPrice);
        bill.setTotalQuantity(totalQuantity);
        bill.setEmailID(user.getEmail());
        bill.setOrderProducts(orderProducts);

        orderTable.setProducts(products);
        orderTable.setUser(user);
        orderTable.setStatus("Not Delivered");
        orderTable.setTotalPrice(totalPrice);
        orderTable.setTotalQuantity(totalQuantity);

        orderRepository.save(orderTable);

        bill.setBillID(orderTable.getOrderId());
        return bill;
    }
}
