package com.app.group15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @PostMapping(value = "/login")
    public String loginPost(@RequestParam(required=false, value = "bannerId") String bannerId, @RequestParam(required=false, value = "password") String password) {

        String USERNAME = "B00848532";
        String PASSWORD = "user";

        // check if banner is valid user

        if(bannerId.equals(USERNAME) && password.equals(PASSWORD))
        {
            return "index";
        }else {
            return "login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet() {
        return "login";
    }

}
