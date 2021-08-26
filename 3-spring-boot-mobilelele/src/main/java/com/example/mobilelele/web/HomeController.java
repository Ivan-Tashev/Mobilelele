package com.example.mobilelele.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller // can contain multiple actions, like @Component
public class HomeController {

    @GetMapping("/home") // request mapping to endpoint, used method GET
    public String home(){
        return "index"; // return response with resource (html, json...)
    } // http://localhost:8080/home
    // or
    @RequestMapping(value = "/home", method = RequestMethod.POST) // Request mapping
    public ModelAndView home(ModelAndView mav) { // action/method
        mav.setViewName("home-view");
        return mav; // returned response view
    }
}
