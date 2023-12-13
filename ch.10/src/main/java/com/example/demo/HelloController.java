package com.example.demo;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHello() {
        return "get hello";
    }

    @PostMapping("/hello")
    public String postHello() {
        return "post hello";
    }

    @PostMapping("/ciao")
    public String postCiao() {
        return "post ciao";
    }

}
