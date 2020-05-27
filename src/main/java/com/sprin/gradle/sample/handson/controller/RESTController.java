package com.sprin.gradle.sample.handson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
@RequestMapping("/")
public class RESTController {

    @Value("${spring.profiles.active:NotFound}")
    private String activeProfile;

    @GetMapping(path = "/")
    public @ResponseBody
    String helloWorld() {
        return "Hello "+activeProfile;
    }

}
