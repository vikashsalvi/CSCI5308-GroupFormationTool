package com.app.group15.UserManagement.LoginManagement;

import com.app.group15.UserManagement.SessionManagement.ISessionService;
import com.app.group15.config.ServiceConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

	private ISessionService sessionService = ServiceConfig.getInstance().getSessionService();
	private ILoginService loginService=ServiceConfig.getInstance().getLoginService();

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(required = false, value = "bannerId") String bannerId, @RequestParam(required = false, value = "password") String password, HttpServletRequest request) {
		
		if (loginService.validateLogin(bannerId, password)) {
			sessionService.setSession(request, "BANNER_ID_SESSION", bannerId);
			String redirect = "redirect:"+sessionService.getUserHome(request);
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
		if (sessionService.isUserSignedIn(request)) {
			String redirect = "redirect:"+sessionService.getUserHome(request);

			return new ModelAndView(redirect);
		}
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		sessionService.destroySession(request);
		return "redirect:/login";
	}
}
