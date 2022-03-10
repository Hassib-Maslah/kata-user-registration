package com.kata.user.service;

import com.kata.user.model.UserDTO;

/**
 * This is a service interface that contains definition of user management methods that should be implemented.
 */
public interface UserService {

    /**
     * This method is used to register new user
     * @param userDTO {@link UserDTO}
     * @return {@link UserDTO}
     */
    UserDTO save(UserDTO userDTO);

    /**
     * This method is used to retrieve user details by user id
     * @param id {@link UserDTO}
     * @return {@link UserDTO}
     */
    UserDTO findById(Long id);
}
