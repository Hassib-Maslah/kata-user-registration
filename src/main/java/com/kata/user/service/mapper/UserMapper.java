package com.kata.user.service.mapper;

import com.kata.user.dao.entity.User;
import com.kata.user.model.UserDTO;

public interface UserMapper {

    UserDTO mapToDTO(User user);

    User mapToEntity(UserDTO userDTO);

}
