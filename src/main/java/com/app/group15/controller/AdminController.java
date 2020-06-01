package com.app.group15.controller;

import com.app.group15.model.Course;
import com.app.group15.model.User;
import com.app.group15.services.AuthorizationService;
import com.app.group15.services.CourseService;
import com.app.group15.services.SessionService;
import com.app.group15.services.UserService;
import org.springframework.stereotype.Controller;
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
				User user = SessionService.getSessionUser(request);
				ArrayList<Course> courses = CourseService.getCoursesList();
				modelAndView = new ModelAndView();
				modelAndView.setViewName("admin/home");
				modelAndView.addObject("user", user);
				modelAndView.addObject("courses", courses);
				return modelAndView;
			} else {
				modelAndView = new ModelAndView("redirect:/login");
				System.out.println("------Redirecting to /login endpoint");
			}
		} else {
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/courses", method = RequestMethod.GET)
	public ModelAndView adminCourses(HttpServletRequest request,
									 @RequestParam(required = false, value = "feedback") String feedback) {
		authorizationService.setAllowedRoles(new String[]{"ADMIN"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = SessionService.getSessionUser(request);
				ArrayList<Course> courses = CourseService.getCoursesList();
				ArrayList<User> userInstructors = CourseService.getAllCourseInstructors(courses);
				modelAndView = new ModelAndView();
				modelAndView.setViewName("admin/courses");
				modelAndView.addObject("user", user);
				modelAndView.addObject("courses", courses);
				modelAndView.addObject("instructorEntities", userInstructors);
				modelAndView.addObject("feedback", feedback);
				return modelAndView;
			} else {
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/courses/add", method = RequestMethod.GET)
	public ModelAndView adminCourseAdd(HttpServletRequest request) {
		authorizationService.setAllowedRoles(new String[]{"ADMIN"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = SessionService.getSessionUser(request);
				ArrayList<User> allUsers = UserService.getAllUsers();
				modelAndView = new ModelAndView();
				modelAndView.setViewName("admin/courseAdd");
				modelAndView.addObject("user", user);
				modelAndView.addObject("allUsers", allUsers);
				return modelAndView;
			} else {
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/courses/add", method = RequestMethod.POST)
	public ModelAndView adminCourseAdd(HttpServletRequest request,
									   @RequestParam(required = true, value = "courseName") String courseName,
									   @RequestParam(required = true, value = "instructorId") int instructorId) {
		authorizationService.setAllowedRoles(new String[]{"ADMIN"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				int courseId = CourseService.addCourse(courseName);
				CourseService.addOrUpdateInstructor(courseId, instructorId);
				UserService.updateUserRole(instructorId, "INSTRUCTOR");
				modelAndView = new ModelAndView("redirect:/admin/courses?feedback=courseAdded");
			} else {
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/courses/edit", method = RequestMethod.GET)
	public ModelAndView adminCourseEdit(HttpServletRequest request,
										@RequestParam(required = true, value = "courseId") int courseId) {
		authorizationService.setAllowedRoles(new String[]{"ADMIN"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = SessionService.getSessionUser(request);
				Course course = CourseService.getCourseDetails(courseId);
				User courseInstructor = CourseService.getCourseInstructor(courseId);
				ArrayList<User> allUsers = UserService.getAllUsers();
				modelAndView = new ModelAndView();
				modelAndView.setViewName("admin/courseEdit");
				modelAndView.addObject("user", user);
				modelAndView.addObject("course", course);
				modelAndView.addObject("courseInstructor", courseInstructor);
				modelAndView.addObject("allUsers", allUsers);
				return modelAndView;
			} else {
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
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
				CourseService.addOrUpdateInstructor(courseId, instructorId);
				UserService.updateUserRole(instructorId, "INSTRUCTOR");
				modelAndView = new ModelAndView("redirect:/admin/courses?feedback=instructorAdded");
			} else {
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/courses/delete", method = RequestMethod.GET)
	public ModelAndView adminCourseDelete(HttpServletRequest request,
										  @RequestParam(required = true, value = "courseId") int courseId){
		authorizationService.setAllowedRoles(new String[]{"ADMIN"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				CourseService.deleteCourse(courseId);
				modelAndView = new ModelAndView("redirect:/admin/courses?feedback=courseDeleted");
			} else{
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else{
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}

}
