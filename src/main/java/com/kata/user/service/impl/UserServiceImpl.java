package com.kata.user.service.impl;

import com.kata.user.dao.repository.UserRepository;
import com.kata.user.model.UserDTO;
import com.kata.user.service.UserService;
import org.springframework.stereotype.Service;

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
}
