package com.kata.user.service;

import com.kata.user.constants.GenderEnum;
import com.kata.user.dao.entity.User;
import com.kata.user.dao.repository.UserRepository;
import com.kata.user.model.UserDTO;
import com.kata.user.service.impl.UserServiceImpl;
import com.kata.user.service.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository, userMapper);
    }

    @Test
    void shouldSaveUserThenReturnRegisteredUser() {
        // arrange
        String username = "Emilie";
        LocalDate birthday = LocalDate.of(1993, 10, 22);
        String country = "France";
        String phone = "0033773125888";
        GenderEnum gender = GenderEnum.FEMALE;
        User user = new User(username, birthday, country, phone, gender);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setBirthday(birthday);
        userDTO.setCountry(country);
        userDTO.setPhone(phone);
        userDTO.setGender(gender);

        when(userRepository.save(any())).thenReturn(user);
        when(userMapper.mapToDTO(any())).thenReturn(userDTO);
        when(userMapper.mapToEntity(any())).thenReturn(user);

        // act
        UserDTO savedUser = userService.save(userDTO);
        // assert
        assertNotNull(savedUser);
        assertThat(savedUser.getUsername()).isEqualTo(username);
        assertThat(savedUser.getBirthday()).isEqualTo(birthday);
        assertThat(savedUser.getCountry()).isEqualTo(country);
        assertThat(savedUser.getPhone()).isEqualTo(phone);
        assertThat(savedUser.getGender()).isEqualTo(gender);
    }

    @Test
    void shouldGetUserThenReturnUserDetails() {
        // arrange
        Long userId = 1L;
        String username = "Emilie";
        LocalDate birthday = LocalDate.of(1993, 10, 22);
        String country = "France";
        String phone = "0033773125888";
        GenderEnum gender = GenderEnum.FEMALE;

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setUsername(username);
        userDTO.setBirthday(birthday);
        userDTO.setCountry(country);
        userDTO.setPhone(phone);
        userDTO.setGender(gender);

        when(userMapper.mapToDTO(any())).thenReturn(userDTO);

        // act
        UserDTO retrievedUser = userService.findById(userId);
        // assert
        assertNotNull(retrievedUser);
        assertThat(retrievedUser.getId()).isEqualTo(userId);
        assertThat(retrievedUser.getUsername()).isEqualTo(username);
        assertThat(retrievedUser.getBirthday()).isEqualTo(birthday);
        assertThat(retrievedUser.getCountry()).isEqualTo(country);
        assertThat(retrievedUser.getPhone()).isEqualTo(phone);
        assertThat(retrievedUser.getGender()).isEqualTo(gender);
    }

}
