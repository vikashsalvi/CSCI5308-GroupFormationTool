package com.sprin.gradle.sample.handson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class RESTController {

    @GetMapping(path = "/yo")
    public @ResponseBody
    String helloWorld() {
        return "Hello";
    }

}
