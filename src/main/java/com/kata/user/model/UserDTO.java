package com.kata.user.model;

import com.kata.user.constants.GenderEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.kata.user.constants.ErrorMessageConstant.VALIDATION_MANDATORY_MSG;

public class UserDTO {

    private Long id;

    @NotNull
    @NotBlank(message = VALIDATION_MANDATORY_MSG)
    private String username;

    @NotNull(message = VALIDATION_MANDATORY_MSG)
    private LocalDate birthday;

    @NotNull
    @NotBlank(message =VALIDATION_MANDATORY_MSG)
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

}
