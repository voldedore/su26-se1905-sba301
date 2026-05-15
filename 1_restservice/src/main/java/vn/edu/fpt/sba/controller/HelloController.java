package vn.edu.fpt.sba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // meta-annotation -> @Controller + @ResponseBody
//@Controller
public class HelloController {
    @GetMapping("/hello")
//    @ResponseBody
    public String hello() {
        return "Hello SBA301";
    }


}
