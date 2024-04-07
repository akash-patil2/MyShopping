package com.MyShopping.MyShopping.service;


import com.MyShopping.MyShopping.models.AppUser;
import com.MyShopping.MyShopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    // to save a user, we need repository layer

    @Autowired
    UserRepository userRepository;

    public void registerUser(AppUser user){
        userRepository.save(user);
    }

    public AppUser getUserById(UUID id){
        AppUser user = userRepository.findById(id).orElse(null);
        return user;
    }
}
