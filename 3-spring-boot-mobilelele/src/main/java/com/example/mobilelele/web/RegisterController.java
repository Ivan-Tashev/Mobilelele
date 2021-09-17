package com.example.mobilelele.web;

import com.example.mobilelele.model.service.UserRegisterServiceModel;
import com.example.mobilelele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        if (!model.containsAttribute("userRegisterModel")) {
            model.addAttribute("userRegisterModel", new UserRegisterServiceModel());
        }
        model.addAttribute("roles", userService.getUserRoles());
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid
                           @ModelAttribute UserRegisterServiceModel userRegisterModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) { // if @Validation errors
            redirectAttributes.addFlashAttribute("userRegisterModel", userRegisterModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegisterModel", bindingResult);
            return "redirect:/users/register";
        }

        if (!userService.checkUsername(userRegisterModel.getUsername())) { // if all correct save User
            userService.saveUser(userRegisterModel);
            return "redirect:/users/login";
        } else { // if Username exist, ask for another...
            redirectAttributes.addFlashAttribute("userRegisterModel", userRegisterModel);
            redirectAttributes.addFlashAttribute("exist", true);
            return "redirect:/users/register";
        }
    }
}
