package com.app.group15.controller;

import com.app.group15.persistence.dao.UserDao;
import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.persistence.injectors.UserDaoInjectorService;
import com.app.group15.services.SignupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SignupController {

	private SignupService signupService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup() {
//		Returns signup page
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("error", false);
		modelAndView.setViewName("signup");
		return modelAndView;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute UserEntity userEntity) {
		System.out.println("Creating user dao");
		UserDao userDao = new UserDaoInjectorService().getUserDao();
//		System.out.println("checking if user exists");
//		System.out.println(userEntity.getEmail());
//		System.out.println(userEntity.getPassword());
//		System.out.println(userEntity.getBannerId());
//		System.out.println(userEntity.getFirstName());
//		System.out.println(userEntity.getLastName());
//		String bannerId=userEntity.getBannerId();
//		System.out.println("line 44");
//		boolean response = signupService.checkUserExists(bannerId);
//		if (response) {
//			ModelAndView modelAndView = new ModelAndView();
//			modelAndView.setViewName("signup");
//			modelAndView.addObject("error", true);
//			modelAndView.addObject("username_error", "Username already exists");
//			System.out.print(modelAndView);
//			return modelAndView;
//		} else {
//			ModelAndView modelAndView = new ModelAndView();
//			modelAndView.setViewName("login");
//			return modelAndView;
//		}
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
