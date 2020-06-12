package com.app.group15.controller;

import com.app.group15.config.AppConfig;
import com.app.group15.config.ServiceConfig;
import com.app.group15.model.Course;
import com.app.group15.model.User;
import com.app.group15.services.AuthorizationService;
import com.app.group15.services.CourseService;
import com.app.group15.services.IAuthorizationService;
import com.app.group15.services.ICourseService;
import com.app.group15.services.ISessionService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
	private IAuthorizationService authorizationService = ServiceConfig.getInstance().getAuthorizationService();
	private ISessionService sessionService = ServiceConfig.getInstance().getSessionService();
	private ICourseService courseService = ServiceConfig.getInstance().getCourseService();
	
	@RequestMapping(value = "/student/home", method = RequestMethod.GET)
	public ModelAndView studentHome(HttpServletRequest request) {
		authorizationService.setAllowedRoles(new String[]{"STUDENT", "TA"});
		ModelAndView modelAndView;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = sessionService.getSessionUser(request);
				List<Course> coursesAsStudent = courseService.getStudentCourses(user.getId());
				List<User> coursesAsStudentInstructors = courseService.getAllCourseInstructors(coursesAsStudent);
				Course courseAsTa = courseService.getStudentCourseAsTa(user.getId());
				User courseAsTaInstructor = courseService.getCourseInstructor(courseAsTa.getId());
				modelAndView = new ModelAndView();
				modelAndView.setViewName("student/home");
				modelAndView.addObject("user", user);
				modelAndView.addObject("coursesAsStudent", coursesAsStudent);
				modelAndView.addObject("coursesAsStudentInstructor", coursesAsStudentInstructors);
				modelAndView.addObject("courseAsTa", courseAsTa);
				modelAndView.addObject("courseAsTaInstructor", courseAsTaInstructor);
			} else {
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/student/courseInfo", method = RequestMethod.GET)
	public ModelAndView studentCourse(HttpServletRequest request,
									  @RequestParam(required = true, value = "courseId") int courseId) {
		authorizationService.setAllowedRoles(new String[]{"STUDENT", "TA"});
		ModelAndView modelAndView;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = sessionService.getSessionUser(request);
				Course course = courseService.getCourseDetails(courseId);
				User courseInstructor = courseService.getCourseInstructor(courseId);
				if (course.getName()!=null){
					modelAndView = new ModelAndView();
					modelAndView.setViewName("student/courseInfo");
					modelAndView.addObject("course", course);
					modelAndView.addObject("courseInstructor", courseInstructor);
					modelAndView.addObject("user", user);
				} else {
					modelAndView = new ModelAndView("redirect:/login");
				}
			} else {
				modelAndView=new ModelAndView("redirect:/login");
			}
		}else{
			modelAndView=new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}
}
