package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PostMapping("/a")
    public String postEndpointA() {
        return "works";
    }

    @GetMapping("a")
    public String getEndpointA() {
        return "works";
    }

    @GetMapping("/a/b")
    public String getEndpointB() {
        return "works";
    }

    @GetMapping("/a/b/c")
    public String getEndpointC() {
        return "works";
    }
}
