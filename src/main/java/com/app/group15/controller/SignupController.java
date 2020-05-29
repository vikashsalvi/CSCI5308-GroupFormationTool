package com.app.group15.controller;

import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.persistence.injectors.UserDaoInjectorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class SignupController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
//		Returns signup page
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost() {
		return "redirect:login";
//		System.out.println(user);
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("user-data-temp");
//		modelAndView.addObject("user_1", user);
//		System.out.print(modelAndView);
//		return modelAndView;
//		call dao for saving the user
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

}
