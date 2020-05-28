package com.app.group15;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
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
