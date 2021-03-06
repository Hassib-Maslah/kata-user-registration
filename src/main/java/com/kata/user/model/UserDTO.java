package com.kata.user.model;

import com.kata.user.constants.GenderEnum;
import com.kata.user.utils.validation.BirthdayConstraint;
import com.kata.user.utils.validation.CountryConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.kata.user.constants.ErrorMessageConstant.*;

/**
 * This is a DTO class for user, used as a request or a response in user REST APIs.
 */
public class UserDTO {

    private Long id;

    @NotNull(message = VALIDATION_MANDATORY_MSG)
    @NotBlank(message = VALIDATION_MANDATORY_MSG)
    private String username;

    @BirthdayConstraint(message = VALIDATION_BIRTHDAY_MSG)
    private LocalDate birthday;

    @CountryConstraint(message = VALIDATION_COUNTRY_MSG)
    private String country;

    private String phone;

    private GenderEnum gender;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, LocalDate birthday, String country, String phone, GenderEnum gender) {
        this.id = id;
        this.username = username;
        this.birthday = birthday;
        this.country = country;
        this.phone = phone;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                '}';
    }
}
