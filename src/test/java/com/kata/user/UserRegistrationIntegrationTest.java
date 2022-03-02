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

import static com.kata.user.constants.ApiUrlConstant.USER_REGISTRATION_API;
import static com.kata.user.constants.ErrorMessageConstant.VALIDATION_ERROR_MSG;
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
        ResponseEntity<UserDTO> response = testRestTemplate.postForEntity(USER_REGISTRATION_API, user, UserDTO.class);
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
        ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity(USER_REGISTRATION_API, user, ErrorResponse.class);
        // assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo(VALIDATION_ERROR_MSG);
        assertThat(response.getBody().getMetaData()).isNotNull();
    }

    /**
     * This test method dedicated to test the scenario when trying to save already exist user with same attributes (username, birthday, country)
     * in the user registration api that should return a bad request error with appropriate message
     */
    @Test
    void makeUserRegistrationReturnsBadRequestWhenUserAlreadyExist() {
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
        testRestTemplate.postForEntity(USER_REGISTRATION_API, user, UserDTO.class);
        ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity(USER_REGISTRATION_API, user, ErrorResponse.class);
        // assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo(VALIDATION_ERROR_MSG);
    }


}
