package com.kata.user.service;

import com.kata.user.model.UserDTO;

public interface UserService {

    /**
     * This method is used to register new user
     * @param userDTO
     * @return {@link UserDTO}
     */
    UserDTO save(UserDTO userDTO);

    /**
     * This method is used to retrieve user details by user id
     * @param id
     * @return {@link UserDTO}
     */
    UserDTO findById(Long id);
}
