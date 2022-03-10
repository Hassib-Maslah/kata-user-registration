package com.kata.user.service.mapper.impl;

import com.kata.user.dao.entity.User;
import com.kata.user.model.UserDTO;
import com.kata.user.service.mapper.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This is a user mapper implementation class that implements {@link UserMapper} interface to handle mapping between user DTOs and Entities with the help of {@link ModelMapper} class.
 */
@Component
public class UserMapperImpl implements UserMapper {

    private final ModelMapper modelMapper;

    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO mapToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User mapToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
