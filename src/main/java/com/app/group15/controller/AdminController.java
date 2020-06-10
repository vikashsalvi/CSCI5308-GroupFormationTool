package com.app.group15.controller;

import com.app.group15.config.AppConfig;
import com.app.group15.model.Course;
import com.app.group15.model.Policy;
import com.app.group15.model.User;
import com.app.group15.services.AuthorizationService;
import com.app.group15.services.CourseService;
import com.app.group15.services.SessionService;
import com.app.group15.services.UserService;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
	private AuthorizationService authorizationService = AppConfig.getInstance().getAuthorizationService();

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
									   @RequestParam(required = false, value = "instructorId") int instructorId) {
		authorizationService.setAllowedRoles(new String[]{"ADMIN"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				int courseId = CourseService.addCourse(courseName);
				if (instructorId!=-1){
					CourseService.addOrUpdateInstructor(courseId, instructorId);
				}
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


	@RequestMapping(value = "/admin/passwordPolicy", method = RequestMethod.GET)
	public ModelAndView passwordPolicyGET(HttpServletRequest request) {
		authorizationService.setAllowedRoles(new String[]{"ADMIN"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = SessionService.getSessionUser(request);
				modelAndView = new ModelAndView();
				modelAndView.setViewName("admin/managePasswordPolicy");
				modelAndView.addObject("user", user);
				modelAndView.addObject("policyList",populateList());
				return modelAndView;
			} else {
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/passwordPolicy", method = RequestMethod.POST)
	public ModelAndView passwordPolicyPOST(HttpServletRequest request,
									 @RequestParam(required = false, value = "policyState") boolean policyState,
									 @RequestParam(required = false, value = "policyValue") String policyValue,
									 @RequestParam(required = false, value = "hidden_policyID") String policyID) {
		authorizationService.setAllowedRoles(new String[]{"ADMIN"});
		ModelAndView modelAndView;

		System.out.println("Policy ID :" + policyID);
		System.out.println("Policy State :"+ policyState);
		System.out.println("Policy Value :" + policyValue);

		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = SessionService.getSessionUser(request);
				modelAndView = new ModelAndView();
				modelAndView.setViewName("admin/managePasswordPolicy");
				modelAndView.addObject("user", user);
				modelAndView.addObject("policyList",populateList());
				return modelAndView;
			} else {
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}


	public ArrayList<Policy> populateList() {
		ArrayList<Policy> policyArrayList = new ArrayList<>();

		Policy policy = new Policy();

		policy.setId(1);
		policy.setPolicy_state(true);
		policy.setPolicy_name("Minimum length");
		policy.setPolicy_value("8");
		policyArrayList.add(policy);

		policy = new Policy();

		policy.setId(2);
		policy.setPolicy_state(true);
		policy.setPolicy_name("Maximum length");
		policy.setPolicy_value("16");
		policyArrayList.add(policy);

		policy = new Policy();

		policy.setId(3);
		policy.setPolicy_state(false);
		policy.setPolicy_name("Minimum number of uppercase characters");
		policy.setPolicy_value("2");
		policyArrayList.add(policy);

		policy = new Policy();

		policy.setId(4);
		policy.setPolicy_state(false);
		policy.setPolicy_name("Minimum number of lowercase characters");
		policy.setPolicy_value("10");
		policyArrayList.add(policy);

		policy = new Policy();

		policy.setId(5);
		policy.setPolicy_state(true);
		policy.setPolicy_name("Minimum number of symbols or special characters");
		policy.setPolicy_value("1");
		policyArrayList.add(policy);

		policy = new Policy();

		policy.setId(6);
		policy.setPolicy_state(true);
		policy.setPolicy_name("A set of characters that are not allowed");
		policy.setPolicy_value("$ * & %");
		policyArrayList.add(policy);

		policy = new Policy();

		policy.setId(7);
		policy.setPolicy_state(false);
		policy.setPolicy_name("Password history constraint");
		policy.setPolicy_value("3");
		policyArrayList.add(policy);

		return policyArrayList;

	}


}
