package com.app.group15.courseManagement;

import com.app.group15.config.AppConfig;
import com.app.group15.config.ServiceConfig;
import com.app.group15.passwordPolicyManagement.IPasswordPolicyService;
import com.app.group15.passwordPolicyManagement.PasswordPolicyAbstractDao;
import com.app.group15.userManagement.IAuthorizationService;
import com.app.group15.userManagement.ISessionService;
import com.app.group15.userManagement.IUserService;
import com.app.group15.userManagement.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {
	private IAuthorizationService authorizationService = ServiceConfig.getInstance().getAuthorizationService();
	private ISessionService sessionService = ServiceConfig.getInstance().getSessionService();
	private ICourseService courseService = ServiceConfig.getInstance().getCourseService();
	private IUserService userService = ServiceConfig.getInstance().getUserService();
	private IPasswordPolicyService passwordPolicyService = ServiceConfig.getInstance().getPasswordPolicy();
	private PasswordPolicyAbstractDao passwordPolicyDao=AppConfig.getInstance().getPasswordPolicyDao();
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView adminHome(HttpServletRequest request) {
		authorizationService.setAllowedRoles(new String[] { "ADMIN" });
		ModelAndView modelAndView;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = sessionService.getSessionUser(request);
				List<Course> courses = courseService.getCoursesList();
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
		authorizationService.setAllowedRoles(new String[] { "ADMIN" });
		ModelAndView modelAndView;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = sessionService.getSessionUser(request);
				List<Course> courses = courseService.getCoursesList();
				List<User> userInstructors = courseService.getAllCourseInstructors(courses);
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
		authorizationService.setAllowedRoles(new String[] { "ADMIN" });
		ModelAndView modelAndView;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = sessionService.getSessionUser(request);
				List<User> allUsers = userService.getAllUsers();
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
			@RequestParam(required = false, value = "instructorId") int instructorId) {
		authorizationService.setAllowedRoles(new String[] { "ADMIN" });
		ModelAndView modelAndView;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				int courseId = courseService.addCourse(courseName);
				if (instructorId != -1) {
					courseService.addOrUpdateInstructor(courseId, instructorId);
				}
				userService.updateUserRole(instructorId, "INSTRUCTOR");
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
		authorizationService.setAllowedRoles(new String[] { "ADMIN" });
		ModelAndView modelAndView;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = sessionService.getSessionUser(request);
				Course course = courseService.getCourseDetails(courseId);
				User courseInstructor = courseService.getCourseInstructor(courseId);
				List<User> allUsers = userService.getAllUsers();
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
		authorizationService.setAllowedRoles(new String[] { "ADMIN" });
		ModelAndView modelAndView;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				courseService.addOrUpdateInstructor(courseId, instructorId);
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
			@RequestParam(required = true, value = "courseId") int courseId) {
		authorizationService.setAllowedRoles(new String[] { "ADMIN" });
		ModelAndView modelAndView;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				courseService.deleteCourse(courseId);
				modelAndView = new ModelAndView("redirect:/admin/courses?feedback=courseDeleted");
			} else {
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}


}
