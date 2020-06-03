package com.app.group15.controller;

import com.app.group15.config.AppConfig;
import com.app.group15.model.Course;
import com.app.group15.model.User;
import com.app.group15.services.AuthorizationService;
import com.app.group15.services.CourseService;
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

@Controller
public class StudentController {
	private AuthorizationService authorizationService = AppConfig.getInstance().getAuthorizationService();

	@RequestMapping(value = "/student/home", method = RequestMethod.GET)
	public ModelAndView studentHome(HttpServletRequest request) {
		authorizationService.setAllowedRoles(new String[]{"STUDENT", "TA"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = SessionService.getSessionUser(request);
				ArrayList<Course> coursesAsStudent = CourseService.getStudentCourses(user.getId());
				ArrayList<User> coursesAsStudentInstructors = CourseService.getAllCourseInstructors(coursesAsStudent);
				Course courseAsTa = CourseService.getStudentCourseAsTa(user.getId());
				User courseAsTaInstructor = CourseService.getCourseInstructor(courseAsTa.getId());
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
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = SessionService.getSessionUser(request);
				Course course = CourseService.getCourseDetails(courseId);
				User courseInstructor = CourseService.getCourseInstructor(courseId);
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
