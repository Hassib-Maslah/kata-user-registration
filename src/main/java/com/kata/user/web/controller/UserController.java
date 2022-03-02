package com.kata.user.web.controller;

import com.kata.user.model.UserDTO;
import com.kata.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.kata.user.constants.ApiUrlConstant.USERS_API;
import static com.kata.user.constants.ApiUrlConstant.USERS_DETAILS_API;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(USERS_API)
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO user) {
        UserDTO response = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(USERS_DETAILS_API)
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO response = userService.findById(id);
        return ResponseEntity.ok().body(response);
    }

}
