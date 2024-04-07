package com.MyShopping.MyShopping.repository;

import com.MyShopping.MyShopping.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository <Product, UUID> {

}
