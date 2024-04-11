package com.MyShopping.MyShopping.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BillDTO {

    UUID billID;
    String buyerName;
    UUID buyerID;
    String emailID;
    int totalQuantity;
    int totalPrice;
    List<OrderProductDTO> orderProducts;
}
