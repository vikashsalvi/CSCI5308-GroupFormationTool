package com.app.group15.controller;

import com.app.group15.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @PostMapping(value = "/login")
    public ModelAndView loginPost(@RequestParam(required=false, value = "bannerId") String bannerId, @RequestParam(required=false, value = "password") String password) {
        LoginService loginService = new LoginService();

        if (loginService.validateLogin(bannerId,password))
        {
            return new ModelAndView("redirect:/");
        }else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            modelAndView.addObject("error", true);
            return modelAndView;
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet() {
        return "login";
    }
}
