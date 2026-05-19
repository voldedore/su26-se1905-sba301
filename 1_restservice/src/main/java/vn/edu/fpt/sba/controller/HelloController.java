package vn.edu.fpt.sba.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // meta-annotation -> @Controller + @ResponseBody
@Tag(name = "Hello API", description = "API for saying hello")
public class HelloController {
    @GetMapping("/hello")
    @Operation(summary = "Simply saying hello", description = "This method returns a string")
    public String hello() {
        return "Hello SBA301";
    }
}
