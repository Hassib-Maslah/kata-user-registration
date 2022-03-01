package com.kata.user.service;

import com.kata.user.constants.GenderEnum;
import com.kata.user.dao.repository.UserRepository;
import com.kata.user.model.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private UserService userService;

    @Test
    void shouldSaveUserThenReturnRegisteredUser() {
        // arrange
        Long userId = 2L;
        String username = "Emilie";
        LocalDate birthday = LocalDate.of(1993, 10, 22);
        String country = "France";
        String phone = "0033773125888";
        GenderEnum gender = GenderEnum.FEMALE;
        UserDTO user = new UserDTO(userId, username, birthday, country, phone, gender);

        when(userRepository.save(any())).thenReturn(user);

        UserDTO userToSave = new UserDTO();
        user.setUsername(username);
        user.setBirthday(birthday);
        user.setCountry(country);
        user.setPhone(phone);
        user.setGender(gender);
        // act

        UserDTO savedUser = userService.save(userToSave);
        // assert
        verify(userService, times(1)).save(any());
        assertNotNull(savedUser);
        assertThat(savedUser.getId()).isEqualTo(userId);
        assertThat(savedUser.getUsername()).isEqualTo(username);
        assertThat(savedUser.getBirthday()).isEqualTo(birthday);
        assertThat(savedUser.getCountry()).isEqualTo(country);
        assertThat(savedUser.getPhone()).isEqualTo(phone);
        assertThat(savedUser.getGender()).isEqualTo(gender);
    }

}
