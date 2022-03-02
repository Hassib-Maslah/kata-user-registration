package com.kata.user;

import com.kata.user.constants.GenderEnum;
import com.kata.user.model.ErrorResponse;
import com.kata.user.model.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static com.kata.user.constants.ApiUrlConstant.USERS_API;
import static com.kata.user.constants.ApiUrlConstant.USERS_DETAILS_API;
import static com.kata.user.constants.ErrorMessageConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * This is an Integration Test class dedicated to test all scenario of the user registration API
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class UserRegistrationIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    /**
     * This test method dedicated to test the happy path scenario of the user registration api
     * that should return a user registered successfully
     */
    @Test
    void makeUserRegistrationReturnsRegisteredUser() {
        // arrange
        String username = "Sam";
        LocalDate birthday =  LocalDate.of(1983, 5, 25);
        String country = "France";
        String phone = "0033558693741";
        GenderEnum gender = GenderEnum.MALE;
        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setBirthday(birthday);
        user.setCountry(country);
        user.setPhone(phone);
        user.setGender(gender);
        // act
        ResponseEntity<UserDTO> response = testRestTemplate.postForEntity(USERS_API, user, UserDTO.class);
        // assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getUsername()).isEqualTo(username);
        assertThat(response.getBody().getBirthday()).isEqualTo(birthday);
        assertThat(response.getBody().getCountry()).isEqualTo(country);
        assertThat(response.getBody().getPhone()).isEqualTo(phone);
        assertThat(response.getBody().getGender()).isEqualTo(gender);
    }

    /**
     * This test method dedicated to test the scenario when passing invalid args for the user registration api
     * that should return a bad request error
     */
    @Test
    void makeUserRegistrationReturnsBadRequestWhenMissingRequiredAttributes() {
        // arrange
        String phone = "0033789693741";
        GenderEnum gender = GenderEnum.FEMALE;
        UserDTO user = new UserDTO();
        user.setPhone(phone);
        user.setGender(gender);
        // act
        ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity(USERS_API, user, ErrorResponse.class);
        // assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo(VALIDATION_ERROR_MSG);
        assertThat(response.getBody().getMetaData()).isNotNull();
    }

    /**
     * This test method dedicated to test the scenario when trying to save already exist user with same attributes (username, birthday, country)
     * in the user registration api that should return a conflict error with appropriate message.
     *
     * <p>Note: The already exist user is created by {@link com.kata.user.bootstrap.UserBootstrap} class</p>
     */
    @Test
    void makeUserRegistrationReturnsConflictWhenUserAlreadyExist() {
        // arrange
        String username = "Jack";
        LocalDate birthday =  LocalDate.of(1993, 9, 5);
        String country = "France";
        String phone = "0033633693012";
        GenderEnum gender = GenderEnum.MALE;
        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setBirthday(birthday);
        user.setCountry(country);
        user.setPhone(phone);
        user.setGender(gender);
        // act
        ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity(USERS_API, user, ErrorResponse.class);
        // assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo(ALREADY_EXIST_DATA_ERROR_MSG);
    }

    /**
     * This test method dedicated to test the happy path scenario of get user details by id API
     * that should return a user details successfully
     */
    @Test
    void getUserReturnsUserDetails() {
        // arrange
        long userId = 1;
        // act
        ResponseEntity<UserDTO> response = testRestTemplate.getForEntity(USERS_DETAILS_API, UserDTO.class, userId);
        // assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
    }

    /**
     * This test method dedicated to test the scenario of get the user details api with unknown user id
     * that should return a user not found error with appropriate message.
     */
    @Test
    void getUserByIdReturnsNotFoundWhenUserNotFound() {
        // arrange
        long userId = 1152;
        // act
        ResponseEntity<ErrorResponse> response = testRestTemplate.getForEntity(USERS_DETAILS_API, ErrorResponse.class, userId);
        // assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo(DATA_NOT_FOUND_ERROR_MSG);
    }



}
