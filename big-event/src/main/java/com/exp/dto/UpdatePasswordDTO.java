package com.exp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UpdatePasswordDTO {

    @NotBlank(message = "Old password must not be blank")
    @NotNull
    @JsonProperty("old_password")
    private String oldPassword;

    @NotBlank(message = "New password must not be blank")
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "New password must be 6–20 characters with letters and numbers only")
    @JsonProperty("new_password")
    private String newPassword;

    @NotBlank(message = "Please confirm the new password")
    @NotNull
    @JsonProperty("re_password")
    private String rePassword;

    public UpdatePasswordDTO() {
    }

    public UpdatePasswordDTO(String oldPassword, String newPassword, String rePassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.rePassword = rePassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}