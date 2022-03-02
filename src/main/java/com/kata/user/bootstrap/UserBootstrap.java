package com.kata.user.bootstrap;

import com.kata.user.constants.GenderEnum;
import com.kata.user.model.UserDTO;
import com.kata.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserBootstrap implements ApplicationRunner {
    Logger log = LoggerFactory.getLogger(UserBootstrap.class);

    private final  UserService userService;

    public UserBootstrap(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserDTO user = new UserDTO();
        user.setUsername("Jack");
        user.setBirthday(LocalDate.of(1993, 9, 5));
        user.setCountry("France");
        user.setPhone("0033633693012");
        user.setGender(GenderEnum.MALE);

        try {
            userService.save(user);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }
}
