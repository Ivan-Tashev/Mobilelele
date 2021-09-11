package com.example.mobilelele.web;

import com.example.mobilelele.model.service.UserLoginServiceModel;
import com.example.mobilelele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String getLogin() {
        return "auth-login";
    }

    @PostMapping("/users/login")
    public String login(UserLoginServiceModel model) {
        if (userService.authenticate(model.getUsername(), model.getPassword())) {
            userService.loginUser(model.getUsername());
            return "redirect:/";
        }
        return "auth-login";
    }
}
