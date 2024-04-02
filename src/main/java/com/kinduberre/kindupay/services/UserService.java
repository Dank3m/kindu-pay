package com.kinduberre.kindupay.services;

import com.kinduberre.kindupay.mappers.UserMapper;
import com.kinduberre.kindupay.models.auth.Roles;
import com.kinduberre.kindupay.models.auth.User;
import com.kinduberre.kindupay.models.dtos.familybank.RegistrationDto;
import com.kinduberre.kindupay.models.dtos.familybank.RegistrationResponseDTO;
import com.kinduberre.kindupay.repositories.auth.RoleRepository;
import com.kinduberre.kindupay.repositories.auth.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserService (UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        List<User> users;
        users = new ArrayList<>(userRepository.findAll());
        return users;
    }

    public RegistrationResponseDTO saveUser(RegistrationDto userRequest) {
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


            Optional<Roles> role = roleRepository.findById(1L);
            Set<Roles> roles = new HashSet<>();
            role.ifPresent(roles::add);
            user.setRoles(roles);
            savedUser = userRepository.save(user);
        } else {
                throw new RuntimeException("Client with client_id: " + userRequest.getClientId() + " already exists");
            }

        return userMapper.userToRegistrationDTO(savedUser);
    }


}
