package com.app.group15.controller;

import com.app.group15.persistence.dao.UserDao;
import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.persistence.injectors.UserDaoInjectorService;
import com.app.group15.services.SignupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SignupController {

	private SignupService signupService = new SignupService();

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
	public ModelAndView signup(@ModelAttribute UserEntity userEntity, @RequestParam("cPassword") String cPassword) {
		System.out.println("Creating user dao");
		System.out.println(cPassword + " " + userEntity.getPassword());
		System.out.println(cPassword.equals(userEntity.getPassword()));
		UserDao userDao = new UserDaoInjectorService().getUserDao();
		String bannerId = userEntity.getBannerId();
		boolean response = signupService.checkUserExists(bannerId);
		if (response) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("signup");
			modelAndView.addObject("error", true);
			modelAndView.addObject("username_error", "Username already exists");
			System.out.print(modelAndView);
			return modelAndView;
		} else if (!cPassword.equals(userEntity.getPassword())) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("signup");
			modelAndView.addObject("error", true);
			modelAndView.addObject("password_error", "Password and confirm password did not match!");
			System.out.print(modelAndView);
			return modelAndView;
		} else {
			int userId = signupService.createUser(userEntity, "GUEST");
			System.out.println(userId);
			return new ModelAndView("redirect:login");
		}

	}
}
