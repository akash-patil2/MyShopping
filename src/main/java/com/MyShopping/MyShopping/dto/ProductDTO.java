package com.MyShopping.MyShopping.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class ProductDTO {
    UUID id;
    String productName;
    String productCategory;
    String rating;
    String description;
    String sellerName;
    int price;
}
