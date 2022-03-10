package com.kata.user.service.mapper;

import com.kata.user.dao.entity.User;
import com.kata.user.model.UserDTO;

/**
 * This is a user mapper interface, that contains method definition of mapping between user DTOs and Entities.
 */
public interface UserMapper {

    UserDTO mapToDTO(User user);

    User mapToEntity(UserDTO userDTO);

}
