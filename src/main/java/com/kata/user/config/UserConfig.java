package com.kata.user.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is a configuration class that contains Spring {@link Bean} definition methods.
 */
@Configuration
public class UserConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
