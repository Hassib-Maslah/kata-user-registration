package com.kata.user.service.mapper.impl;

import com.kata.user.dao.entity.User;
import com.kata.user.model.UserDTO;
import com.kata.user.service.mapper.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
