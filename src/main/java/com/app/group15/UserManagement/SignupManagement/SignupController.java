package com.app.group15.UserManagement.SignupManagement;

import com.app.group15.PasswordPolicyManagement.IPasswordPolicyService;
import com.app.group15.PasswordPolicyManagement.PasswordPolicyValidationResult;
import com.app.group15.UserManagement.User;
import com.app.group15.config.ServiceConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SignupController {

	private ISignupService signupService = ServiceConfig.getInstance().getSignUpService();
	private IPasswordPolicyService passwordPolicyService=ServiceConfig.getInstance().getPasswordPolicy();
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("error", false);
		modelAndView.setViewName("signup");
		return modelAndView;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute User user, @RequestParam("cPassword") String cPassword) {
		String bannerId = user.getBannerId();
		boolean response = signupService.checkUserExists(bannerId);
		PasswordPolicyValidationResult result=passwordPolicyService.validatePassword(user.getPassword(), -1);
		if (response) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("signup");
			modelAndView.addObject("error", true);
			modelAndView.addObject("bannerId_error", "Banner Id already registered!");
			return modelAndView;
		} else if (!cPassword.equals(user.getPassword())) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("signup");
			modelAndView.addObject("error", true);
			modelAndView.addObject("password_error", "Password and confirm password did not match!");
			return modelAndView;
		} else if(!result.getResult()){
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("signup");
			modelAndView.addObject("error", true);
			modelAndView.addObject("password_error", result.isMessage());
			return modelAndView;
		}
		else {
			int userId = signupService.createUser(user, "GUEST");
			return new ModelAndView("redirect:login");
		}

	}
}
