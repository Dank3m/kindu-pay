package com.kinduberre.kindupay.services;

import com.kinduberre.kindupay.models.auth.User;
import com.kinduberre.kindupay.models.dtos.familybank.RegistrationDto;
import com.kinduberre.kindupay.models.dtos.familybank.TokenRequest;
import com.kinduberre.kindupay.repositories.auth.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public User saveUser(RegistrationDto userRequest) {
        if(userRequest.getClientId() == null){
            throw new RuntimeException("Parameter client_id is not found in request..!!");
        } else if(userRequest.getClientSecret() == null){
            throw new RuntimeException("Parameter client_secret is not found in request..!!");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = userRequest.getClientSecret();
        String encodedPassword = encoder.encode(rawPassword);

        User savedUser = new User();

        if(userRepository.findByUsername(userRequest.getClientId()).isEmpty()){
            User user = new User();
            user.setPassword(encodedPassword);
            user.setUsername(userRequest.getClientId());
            user.setFullName(userRequest.getFullName());

            savedUser = userRepository.save(user);
        } else {
                throw new RuntimeException("Client with client_id: " + userRequest.getClientId() + " already exists");
            }

        return savedUser;
    }


}
