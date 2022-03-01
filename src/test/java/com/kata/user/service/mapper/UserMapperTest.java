package com.kata.user.service.mapper;

import com.kata.user.constants.GenderEnum;
import com.kata.user.dao.entity.User;
import com.kata.user.model.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {

    @Mock
    private ModelMapper modelMapper;

    @Autowired
    @InjectMocks
    private UserMapper userMapper;

    private UserDTO userDTO;

    private User user;

    @BeforeEach
    public void setUp() {
        // initialize users for test
        String username = "James";
        LocalDate birthday = LocalDate.of(1993, 10, 22);
        String country = "France";
        String phone = "0033773125888";
        GenderEnum gender = GenderEnum.MALE;
        userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setBirthday(birthday);
        userDTO.setCountry(country);
        userDTO.setPhone(phone);
        userDTO.setGender(gender);

        user = new User(username, birthday, country, phone, gender);
    }

    @AfterEach
    public void clear() {
        userDTO = null;
        user = null;
    }

    @Test
    void shouldMapUserToUserDTOThenReturnUserDTO() {
        // arrange
        when(modelMapper.map(any(User.class), any(UserDTO.class))).thenReturn(userDTO);
        // act
        UserDTO userMapped = userMapper.mapToDTO(user);
        // assert
        verify(userMapper, times(1)).mapToDTO(any());
        assertNotNull(userMapped);
        assertThat(userMapped.getId()).isNotNull();
        assertEquals(userMapped.getUsername(), userDTO.getUsername());
        assertEquals(userMapped.getBirthday(), userDTO.getBirthday());
        assertEquals(userMapped.getCountry(), userDTO.getCountry());
        assertEquals(userMapped.getGender(), userDTO.getGender());
        assertEquals(userMapped.getPhone(), userDTO.getPhone());
    }

    @Test
    void shouldMapUserDTOToUserThenReturnUser() {
        // arrange
        when(modelMapper.map(any(UserDTO.class), any(User.class))).thenReturn(user);
        // act
        User userMapped = userMapper.mapToEntity(userDTO);
        // assert
        verify(userMapper, times(1)).mapToEntity(any());
        assertNotNull(userMapped);
        assertThat(userMapped.getId()).isNotNull();
        assertEquals(userMapped.getUsername(), user.getUsername());
        assertEquals(userMapped.getBirthday(), user.getBirthday());
        assertEquals(userMapped.getCountry(), user.getCountry());
        assertEquals(userMapped.getGender(), user.getGender());
        assertEquals(userMapped.getPhone(), user.getPhone());
    }

}
