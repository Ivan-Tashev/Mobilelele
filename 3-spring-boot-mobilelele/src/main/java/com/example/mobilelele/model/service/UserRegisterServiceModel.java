package com.example.mobilelele.model.service;

import com.example.mobilelele.model.entity.enums.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterServiceModel {
    @NotNull
    @Size(min = 2, max = 20, message = "Name required, from 2 to 20 symbols.")
    private String firstName;
    @NotNull
    @Size(min = 2, max = 20, message = "Name required, from 2 to 20 symbols.")
    private String lastName;
    @NotNull
    @Size(min = 2, max = 20, message = "Username required, from 2 to 20 symbols.")
    private String username;
    @NotNull
    @Size(min = 3, max = 20, message = "Password required, from 3 to 20 symbols.")
    private String password;
    @NotNull (message = "Role is required.")
    private Role roles;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }
}
