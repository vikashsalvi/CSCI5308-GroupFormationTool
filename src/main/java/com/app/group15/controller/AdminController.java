package com.app.group15.controller;

import com.app.group15.persistence.entity.CourseEntity;
import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.services.AuthorizationService;
import com.app.group15.services.CourseService;
import com.app.group15.services.SessionService;
import com.app.group15.services.UserService;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class AdminController {
	private AuthorizationService authorizationService = new AuthorizationService();

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView adminHome(HttpServletRequest request) {
		authorizationService.setAllowedRoles(new String[]{"ADMIN"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				UserEntity userEntity = SessionService.getSessionUser(request);
				ArrayList<CourseEntity> courseEntities = CourseService.getCoursesList();
				modelAndView = new ModelAndView();
				modelAndView.setViewName("admin/home");
				modelAndView.addObject("userEntity", userEntity);
				modelAndView.addObject("courseEntities", courseEntities);
				return modelAndView;
			} else {
				System.out.println("----------------Unauthorized access for /admin/home !!!----------------");
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			System.out.println("----------------User not logged in !!!----------------");
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/courses", method = RequestMethod.GET)
	public ModelAndView adminCourses(HttpServletRequest request) {
		authorizationService.setAllowedRoles(new String[]{"ADMIN"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				UserEntity userEntity = SessionService.getSessionUser(request);
				ArrayList<CourseEntity> courseEntities = CourseService.getCoursesList();
				ArrayList<UserEntity> userEntitiesInstructors=CourseService.getAllCourseInstructors(courseEntities);
				modelAndView = new ModelAndView();
				modelAndView.setViewName("admin/courses");
				modelAndView.addObject("userEntity", userEntity);
				modelAndView.addObject("courseEntities", courseEntities);
				modelAndView.addObject("instructorEntities", userEntitiesInstructors);
				return modelAndView;
			} else {
				System.out.println("----------------Unauthorized access for /admin/home !!!----------------");
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			System.out.println("----------------User not logged in !!!----------------");
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/courses/edit", method = RequestMethod.GET)
	public ModelAndView adminCourseEdit(HttpServletRequest request,
										@RequestParam(required = true, value = "courseId") int id) {
		authorizationService.setAllowedRoles(new String[]{"ADMIN"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				UserEntity userEntity = SessionService.getSessionUser(request);
				CourseEntity courseEntity = CourseService.getCourseDetails(id);
				UserEntity courseInstructor = CourseService.getCourseInstructor(id);
				ArrayList<UserEntity> allUsers= UserService.getAllUsers();
				modelAndView = new ModelAndView();
				modelAndView.setViewName("admin/courseEdit");
				modelAndView.addObject("userEntity", userEntity);
				modelAndView.addObject("courseEntity", courseEntity);
				modelAndView.addObject("courseInstructor", courseInstructor);
				modelAndView.addObject("allUsers", allUsers);
				return modelAndView;
			} else {
				System.out.println("----------------Unauthorized access for /admin/home !!!----------------");
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			System.out.println("----------------User not logged in !!!----------------");
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}
	@RequestMapping(value = "/admin/courses/edit", method = RequestMethod.POST)
	public ModelAndView adminCourseEdit(HttpServletRequest request,
										@RequestParam(required = true, value = "courseId") int courseId,
										@RequestParam(required = true, value = "instructorId") int instructorId) {
		authorizationService.setAllowedRoles(new String[]{"ADMIN"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				UserEntity userEntity = SessionService.getSessionUser(request);
				CourseEntity courseEntity = CourseService.getCourseDetails(courseId);
				UserEntity courseInstructor = CourseService.getCourseInstructor(courseId);
				ArrayList<UserEntity> allUsers= UserService.getAllUsers();
				modelAndView = new ModelAndView();
				modelAndView.setViewName("admin/courseEdit");
				modelAndView.addObject("userEntity", userEntity);
				modelAndView.addObject("courseEntity", courseEntity);
				modelAndView.addObject("courseInstructor", courseInstructor);
				modelAndView.addObject("allUsers", allUsers);
				return modelAndView;
			} else {
				System.out.println("----------------Unauthorized access for /admin/home !!!----------------");
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			System.out.println("----------------User not logged in !!!----------------");
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}
}
