package com.MyShopping.MyShopping.repository;

import com.MyShopping.MyShopping.models.OrderTable;
import org.hibernate.boot.model.source.spi.Orderable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderTable, UUID> {

}
