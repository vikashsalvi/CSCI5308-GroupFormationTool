package com.app.group15.controller;

import com.app.group15.config.AppConfig;
import com.app.group15.model.User;
import com.app.group15.services.AuthorizationService;
import com.app.group15.services.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class StudentController {
	private AuthorizationService authorizationService = AppConfig.getInstance().getAuthorizationService();

//	@RequestMapping(value = "/student/home", method = RequestMethod.GET)
//	public ModelAndView studentHome(HttpServletRequest request) {
//		authorizationService.setAllowedRoles(new String[]{"STUDENT","TA"});
//		ModelAndView modelAndView;
//		if (SessionService.isUserSignedIn(request)) {
//			if (authorizationService.isAuthorized(request)) {
//				User user = SessionService.getSessionUser(request);
//			}
//		}
//	}

	//
//	@RequestMapping(value = "/upload", method = RequestMethod.GET)
//	public ModelAndView studentHome(HttpServletRequest request) {
////		authorizationService.setAllowedRoles(new String[]{"GUEST"});
//////		request.isUserInRole()
////		request.getAttribute();
////		ModelAndView modelAndView;
////		if (SessionService.isUserSignedIn(request)) {
////			if (authorizationService.isAuthorized(request)) {
////
////			}
////		}
//		return new ModelAndView("temp_upload");
//
//	}
//
//	@RequestMapping(value = "/upload", method = RequestMethod.POST)
//	public ModelAndView adminCourseDelete(HttpServletRequest request,
//										  RedirectAttributes redirectAttributes,
//										  @RequestParam(required = true, value = "file") MultipartFile file) {
//		if (!file.isEmpty()) {
//			try {
//				byte[] bytes = file.getBytes();
//				String completeData = new String(bytes);
//
//				redirectAttributes.addFlashAttribute("message",
//					"You successfully uploaded " + file.getOriginalFilename() + "!");
//				String[] rows = completeData.split("\n");
//				int i = 0;
//				for (String row : rows) {
//					i++;
//					System.out.println(i + row);
//				}
////
////				String[] columns = rows[0].split(",");
////				System.out.println();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
////		authorizationService.setAllowedRoles(new String[]{"GUEST"});
//////		request.isUserInRole()
////		request.getAttribute();
////		ModelAndView modelAndView;
////		if (SessionService.isUserSignedIn(request)) {
////			if (authorizationService.isAuthorized(request)) {
////
////			}
////		}
//		return new ModelAndView("redirect:/upload");
//
//	}
}
