package com.MyShopping.MyShopping.repository;

import com.MyShopping.MyShopping.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// This repository going to deal with AppUser Table
@Repository
public interface UserRepository extends JpaRepository <AppUser, UUID>{


}
