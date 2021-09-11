package com.example.mobilelele.service;

public interface UserService {

    // return true, if user authenticated successfully
    boolean authenticate(String username, String password);

    // set CurrentUser to the Logged-In user
    void loginUser(String username);


}
