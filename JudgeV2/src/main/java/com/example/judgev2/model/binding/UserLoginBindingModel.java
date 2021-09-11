package com.example.judgev2.model.binding;

import org.hibernate.validator.constraints.Length;

public class UserLoginBindingModel {
    @Length(min = 2, message = "username length must be min 2 chars")
    private String username;
    @Length(min = 3, message = "password length must be min 3 chars")
    private String password;

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
