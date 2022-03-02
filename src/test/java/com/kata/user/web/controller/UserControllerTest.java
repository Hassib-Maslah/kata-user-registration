package com.kata.user.web.controller;

import com.kata.user.constants.GenderEnum;
import com.kata.user.model.UserDTO;
import com.kata.user.service.UserService;
import com.kata.user.utils.JsonUtils;
import com.kata.user.web.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.kata.user.constants.ApiUrlConstant.USERS_API;
import static com.kata.user.constants.ApiUrlConstant.USERS_DETAILS_API;
import static com.kata.user.constants.ErrorMessageConstant.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void makeUserRegistrationShouldRegisterUser() throws Exception {
        // prepare a mock for save method in user service class
        Long userId = 1L;
        String username = "john";
        LocalDate birthday = LocalDate.of(1990, 2, 2);
        String country = "France";
        String phone = "0033143485548";
        GenderEnum gender = GenderEnum.MALE;

        UserDTO user = new UserDTO(userId, username, birthday, country, phone, gender);

        given(userService.save(any())).willReturn(user);
        // prepare a request with all valid and required attributes
        UserDTO userToSave = new UserDTO();
        userToSave.setUsername(username);
        userToSave.setBirthday(birthday);
        userToSave.setCountry(country);
        userToSave.setPhone(phone);
        userToSave.setGender(gender);
        // invoke and check the received response
        mockMvc.perform(post(USERS_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(userToSave))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.username").value(username))
                .andExpect(jsonPath("$.birthday").value(birthday.toString()))
                .andExpect(jsonPath("$.country").value(country))
                .andExpect(jsonPath("$.phone").value(phone))
                .andExpect(jsonPath("$.gender").value(gender.name()));
    }

    @Test
    public void makeUserRegistrationShouldReturnsBadRequestWhenMissingRequiredAttributes() throws Exception {
        // prepare a user with missed required attributes (username, country, birthday)
        UserDTO userToSave = new UserDTO();
        userToSave.setPhone("0033143396693");
        userToSave.setGender(GenderEnum.FEMALE);
        // invoke and check the received response
        mockMvc.perform(post(USERS_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(userToSave))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.message").value(VALIDATION_ERROR_MSG))
                .andExpect(jsonPath("$.metaData").isNotEmpty())
                .andExpect(jsonPath("$.metaData.username").value(VALIDATION_MANDATORY_MSG))
                .andExpect(jsonPath("$.metaData.birthday").value(VALIDATION_BIRTHDAY_MSG))
                .andExpect(jsonPath("$.metaData.country").value(VALIDATION_COUNTRY_MSG));
    }


    @Test
    public void makeUserRegistrationShouldReturnsBadRequestWhenPassingInvalidCountry() throws Exception {
        // prepare a non French residents (country = Allmagne)
        UserDTO userToSave = new UserDTO();
        userToSave.setUsername("Josh");
        userToSave.setBirthday(LocalDate.of(1990, 2, 2));
        userToSave.setCountry("Allmagne");
        userToSave.setPhone("0033589396693");
        userToSave.setGender(GenderEnum.MALE);
        // invoke and check the received response
        mockMvc.perform(post(USERS_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(userToSave))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.message").value(VALIDATION_ERROR_MSG))
                .andExpect(jsonPath("$.metaData").isNotEmpty())
                .andExpect(jsonPath("$.metaData.country").value(VALIDATION_COUNTRY_MSG));
    }

    @Test
    public void makeUserRegistrationShouldReturnsBadRequestWhenPassingInvalidBirthday() throws Exception {
        // prepare a user with a birthday smaller than 18 years
        UserDTO userToSave = new UserDTO();
        userToSave.setUsername("Josh");
        userToSave.setBirthday(LocalDate.of(2015, 2, 2));
        userToSave.setCountry("France");
        userToSave.setPhone("0033589396693");
        userToSave.setGender(GenderEnum.MALE);
        // invoke and check the received response
        mockMvc.perform(post(USERS_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(userToSave))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.message").value(VALIDATION_ERROR_MSG))
                .andExpect(jsonPath("$.metaData").isNotEmpty())
                .andExpect(jsonPath("$.metaData.birthday").value(VALIDATION_BIRTHDAY_MSG));
    }

    @Test
    public void getUserShouldReturnsUserDetails() throws Exception {
        // prepare a mock for save method in user service class
        Long userId = 1L;
        String username = "john";
        LocalDate birthday = LocalDate.of(1990, 2, 2);
        String country = "France";
        String phone = "0033143485548";
        GenderEnum gender = GenderEnum.MALE;

        UserDTO user = new UserDTO(userId, username, birthday, country, phone, gender);

        given(userService.findById(any())).willReturn(user);
        // invoke and check the received response
        mockMvc.perform(get(USERS_DETAILS_API, userId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.username").value(username))
                .andExpect(jsonPath("$.birthday").value(birthday.toString()))
                .andExpect(jsonPath("$.country").value(country))
                .andExpect(jsonPath("$.phone").value(phone))
                .andExpect(jsonPath("$.gender").value(gender.name()));
    }

    @Test
    public void getUserShouldReturnsNotFoundErrorWhenPassingUnknownUserId() throws Exception {
        // prepare the request
        long userId = 1152;

        given(userService.findById(any())).willThrow(UserNotFoundException.class);

        // invoke and check the received response
        mockMvc.perform(get(USERS_DETAILS_API, userId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.message").value(DATA_NOT_FOUND_ERROR_MSG));
    }

}
