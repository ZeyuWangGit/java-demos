package com.exp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserRegisterDTO {

    @NotBlank(message = "Username must not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "Username must be 4–16 characters, only letters, numbers, and underscores allowed")
    private String username;

    @NotBlank(message = "Password must not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "Password must be 6–20 characters, only letters and numbers allowed")
    private String password;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}