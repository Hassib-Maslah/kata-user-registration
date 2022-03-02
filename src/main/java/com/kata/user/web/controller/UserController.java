package com.kata.user.web.controller;

import com.kata.user.model.UserDTO;
import com.kata.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.kata.user.constants.ApiUrlConstant.USERS_API;
import static com.kata.user.constants.ApiUrlConstant.USERS_DETAILS_API;

/**
 * This is a User management REST Controller dedicated to handle all types of APIs for.
 **/
 @RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This method represent a User registration API.
     * Contains validation of the request body with usage of the {@link Valid} annotation.
     * Invoked with a POST HTTP method.
     * @param user {@link UserDTO}
     * @return {@link ResponseEntity<UserDTO>}
     */
    @PostMapping(USERS_API)
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO user) {
        UserDTO response = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * This method represent a User details API.
     * Invoked with a GET HTTP method.
     * @param id {@link Long}
     * @return {@link ResponseEntity<UserDTO>}
     */
    @GetMapping(USERS_DETAILS_API)
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO response = userService.findById(id);
        return ResponseEntity.ok().body(response);
    }

}
