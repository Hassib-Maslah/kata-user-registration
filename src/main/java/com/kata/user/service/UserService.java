package com.kata.user.service;

import com.kata.user.model.UserDTO;

public interface UserService {

    /**
     * This method is used to register new User
     * @param userDTO
     * @return
     */
    UserDTO save(UserDTO userDTO);
}
