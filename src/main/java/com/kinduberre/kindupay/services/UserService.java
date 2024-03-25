package com.kinduberre.kindupay.services;

import com.kinduberre.kindupay.models.auth.User;
import com.kinduberre.kindupay.repositories.auth.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> users;
        users = new ArrayList<>(userRepository.findAll());
        return users;
    }
}
