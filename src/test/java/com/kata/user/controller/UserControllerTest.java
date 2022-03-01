package com.kata.user.controller;

import com.kata.user.model.UserDTO;
import com.kata.user.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.kata.user.constants.ApiUrlConstant.USER_REGISTRATION_API;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void makeUserRegistration_shouldRegisteredUser() throws Exception {
        Long userId = 1L;
        String username = "john";
        LocalDate birthday =  LocalDate.of(1990, 2, 2);
        String country = "France";
        String phone = "0033143485548";
        String gender = "Mr";

        UserDTO user = new UserDTO(userId, username, birthday, country, phone, gender);

        given(userService.save(any())).willReturn(user);

        UserDTO userToSave = new UserDTO();
        user.setUsername(username);
        user.setBirthday(birthday);
        user.setCountry(country);
        user.setPhone(phone);
        user.setGender(gender);

        mockMvc.perform(post(USER_REGISTRATION_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(userToSave))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.username").value(birthday))
                .andExpect(jsonPath("$.country").value(country))
                .andExpect(jsonPath("$.phone").value(phone))
                .andExpect(jsonPath("$.gender").value(gender));
    }


}
