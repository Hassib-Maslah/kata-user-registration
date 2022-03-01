package com.kata.user.controller;

import com.kata.user.model.UserDTO;
import com.kata.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.kata.user.constants.ApiUrlConstant.USER_REGISTRATION_API;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(USER_REGISTRATION_API)
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO user) {
        UserDTO response = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
