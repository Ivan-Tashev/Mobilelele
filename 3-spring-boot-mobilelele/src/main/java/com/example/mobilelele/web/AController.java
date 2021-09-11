package com.example.mobilelele.web;

import com.example.mobilelele.model.entity.Brand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AController {

    // GET /a?id=5 HTTP/1.1, Host: localhost:8080
    @GetMapping("/a")  // @Request param from Query string (?id=5)
    public String getA(Model model, @RequestParam(value = "id", defaultValue = "") String id) {
        model.addAttribute("id", id);
        model.addAttribute("obj", List.of("A" + id, "B" + (id + 1)));
        model.addAttribute("brands", List.of(new Brand("Brand1"), new Brand("Brand2")));
        return "a";
    }

    // POST /a HTTP/1.1, Host: localhost:8080
    @PostMapping("/a") // @Request param from <form> data (id=5)
    public String getB(Model model, @RequestParam(value = "id", defaultValue = "") String id) {
        model.addAttribute("ids", id);
        return "a";
    }

    @ModelAttribute // add Model Attributes to all models in the Controller
    public void addAttributeToAllMappings(Model model) {
        model.addAttribute("message", "Welcome!");
    }

//    @PostMapping("/car/add") // argument retrieved from Model
//    public String submit(@ModelAttribute("car") Car car){
//        return "carView";
//    }

//    @CrossOrigin
//    @RequestMapping(value = "/a", method = RequestMethod.GET)
//    public String getA() {
//        return "A";
//    }

//    @Component
//    @Qualifier("bike")
//    public Bike implements Vechicle {
//        private String make;
//    }
//
//    Biker(Qualifier("bike") Vechicle vechicle) { this.vechicle = vechicle; }

    @GetMapping("/header")
    public ResponseEntity<String> header(
            @RequestHeader("accept-language") String language) {
        System.out.println(language); // bg-BG,bg;q=0.9
        return new ResponseEntity<String>(language, HttpStatus.OK);
    }

    @RequestMapping(value = "/response", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public String storeBrand(@RequestBody Brand brand){
        return "a";
    }

//    @PostMapping("/car/add")
//    public ResponseEntity addCar(@RequestBody CarBindingModel bindModel){
//        myService.add(bindModel);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }
//
//    @GetMapping("/brand")
//    @ResponseBody // serialize brand to JSON, and pass back to client
//    public Brand getBrand(){
//        return brand;
//    }
}
