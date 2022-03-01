package com.kata.user;

import com.kata.user.model.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class UserRegistrationIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void makeUserRegistration_returnsRegisteredUser() {
        // arrange
        UserDTO request = new UserDTO();
        // act
        ResponseEntity<UserDTO> response = testRestTemplate.postForEntity("/user-management/users", request, UserDTO.class);
        // assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getUsername()).isEqualTo(request.getUsername());
    }

}
