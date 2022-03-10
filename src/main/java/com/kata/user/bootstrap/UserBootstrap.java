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

/**
 * This Bootstrap class dedicated to initialize data in DB before application startup completes.
 */
@Component
public class UserBootstrap implements ApplicationRunner {
    Logger log = LoggerFactory.getLogger(UserBootstrap.class);

    private final  UserService userService;

    public UserBootstrap(UserService userService) {
        this.userService = userService;
    }

    /**
     * This is the main method to be call by Spring framework before application startup completes.
     * It contains logic for registration of a user in the DB.
     * @param args
     * @throws Exception
     */
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
