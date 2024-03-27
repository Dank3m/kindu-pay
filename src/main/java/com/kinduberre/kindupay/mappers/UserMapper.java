package com.kinduberre.kindupay.mappers;

import com.kinduberre.kindupay.models.auth.User;
import com.kinduberre.kindupay.models.dtos.familybank.RegistrationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "clientId", source = "user.username")
    RegistrationResponseDTO userToRegistrationDTO(User user);
}
