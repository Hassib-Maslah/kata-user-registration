package com.kata.user.service.impl;

import com.kata.user.dao.repository.UserRepository;
import com.kata.user.model.UserDTO;
import com.kata.user.service.UserService;
import com.kata.user.service.mapper.UserMapper;
import com.kata.user.web.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This is a service implementation class that implements {@link UserService} interface to handle business logic for the user management.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return userMapper.mapToDTO(
                userRepository.save(userMapper.mapToEntity(userDTO))
        );
    }

    @Override
    public UserDTO findById(Long id) {
        UserDTO userDTO;

        try {
            userDTO = userMapper.mapToDTO(userRepository.getById(id));
        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }

        return userDTO;
    }

}
