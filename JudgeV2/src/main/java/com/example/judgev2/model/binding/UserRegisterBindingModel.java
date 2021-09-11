package com.example.judgev2.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

    public class UserRegisterBindingModel {
        @Length(min = 2, message = "username length must be min 2 chars")
        private String username;
        @Length(min = 3, message = "password length must be min 3 chars")
        private String password;
        private String confirmPassword;
        @Email(message = "enter valid email address")
        private String email;
        @Pattern(regexp = "https://github.com/.+", message = "enter valid git address")
        private String git;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }
}
