package com.example.mobilelele.web;

import com.example.mobilelele.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // can contain multiple actions, like @Component
@RequestMapping("/brands") // used like path prefix, or path map
public class BrandsController { // in web package
    private final BrandService brandService;

    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all") // request mapping to endpoint, used method GET
    public String allBrands(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        return "brands"; // return response with resource (html, json...)
    } // http://localhost:8080/brands/all



    //--------------------------- EDUCATION PURPOSES BELLOW !!! -------------------------------------------

    @GetMapping("/details/{id}")
    @ResponseBody // return response body (5)
    public String getBrandId(@PathVariable Long id) { //get path variable
        return String.valueOf(id); // -> 5
    } // http://localhost:8080/brands/details/5

    @PostMapping("/register")
    public String register(@RequestParam("name") String name, @RequestParam int age) {
        System.out.println("Name: " + name + " Age: " + age); // get params from <Form>
        return "redirect:/home"; // POST redirect GET patter
    } // redirect to a page after get request, not to submit form again!

    @GetMapping("/") // passing String to the View
    public String welcome(Model model){ // Model obj passed to View as context
        model.addAttribute("name", "John Smith");
        return "index"; // attributes are accessed from Thymeleaf
    }
/*
    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO userDto){
        // input field names MUST be same as obj field names
        return "redirect:/login"; // in case of some error
    }
 */
}
