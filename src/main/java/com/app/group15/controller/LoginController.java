package com.app.group15.controller;

import com.app.group15.services.LoginService;
import com.app.group15.services.SessionServices;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(required = false, value = "bannerId") String bannerId, @RequestParam(required = false, value = "password") String password, HttpServletRequest request) {
		LoginService loginService = new LoginService();
		if (loginService.validateLogin(bannerId, password)) {
			SessionServices.setSession(request, "BANNER_ID_SESSION", bannerId);
			String redirect = SessionServices.getUserHome(request);
			return new ModelAndView(redirect);
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("login");
			modelAndView.addObject("error", true);
			return modelAndView;
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		if (SessionServices.isUserSignedIn(request)) {
			String redirect = SessionServices.getUserHome(request);
			return new ModelAndView(redirect);
		}
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		SessionServices.destroySession(request);
		return "redirect:/login";
	}
}
