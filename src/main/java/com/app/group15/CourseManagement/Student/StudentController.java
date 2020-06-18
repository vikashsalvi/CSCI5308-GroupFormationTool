package com.app.group15.CourseManagement.Student;

import com.app.group15.Config.ServiceConfig;
import com.app.group15.CourseManagement.Course;
import com.app.group15.CourseManagement.ICourseService;
import com.app.group15.UserManagement.SessionManagement.IAuthorizationService;
import com.app.group15.UserManagement.SessionManagement.ISessionService;
import com.app.group15.UserManagement.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StudentController {
    private IAuthorizationService authorizationService = ServiceConfig.getInstance().getAuthorizationService();
    private ISessionService sessionService = ServiceConfig.getInstance().getSessionService();
    private ICourseService courseService = ServiceConfig.getInstance().getCourseService();

    @RequestMapping(value = "/student/home", method = RequestMethod.GET)
    public ModelAndView studentHome(HttpServletRequest request) {
        authorizationService.setAllowedRoles(new String[]{"STUDENT", "TA"});
        ModelAndView modelAndViewResponse;
        if (sessionService.isUserSignedIn(request)) {
            if (authorizationService.isAuthorized(request)) {
                User user = sessionService.getSessionUser(request);
                List<Course> coursesAsStudent = courseService.getStudentCourses(user.getId());
                List<User> coursesAsStudentInstructors = courseService.getAllCourseInstructors(coursesAsStudent);
                Course courseAsTa = courseService.getStudentCourseAsTa(user.getId());
                User courseAsTaInstructor = courseService.getCourseInstructor(courseAsTa.getId());
                modelAndViewResponse = new ModelAndView();
                modelAndViewResponse.setViewName("student/home");
                modelAndViewResponse.addObject("user", user);
                modelAndViewResponse.addObject("coursesAsStudent", coursesAsStudent);
                modelAndViewResponse.addObject("coursesAsStudentInstructor", coursesAsStudentInstructors);
                modelAndViewResponse.addObject("courseAsTa", courseAsTa);
                modelAndViewResponse.addObject("courseAsTaInstructor", courseAsTaInstructor);
            } else {
                modelAndViewResponse = new ModelAndView("redirect:/login");
            }
        } else {
            modelAndViewResponse = new ModelAndView("redirect:/login");
        }
        return modelAndViewResponse;
    }

    @RequestMapping(value = "/student/courseInfo", method = RequestMethod.GET)
    public ModelAndView studentCourse(HttpServletRequest request,
                                      @RequestParam(required = true, value = "courseId") int courseId) {
        authorizationService.setAllowedRoles(new String[]{"STUDENT", "TA"});
        ModelAndView modelAndViewResponse;
        if (sessionService.isUserSignedIn(request)) {
            if (authorizationService.isAuthorized(request)) {
                User user = sessionService.getSessionUser(request);
                Course course = courseService.getCourseDetails(courseId);
                User courseInstructor = courseService.getCourseInstructor(courseId);
                if (course.getName() != null) {
                    modelAndViewResponse = new ModelAndView();
                    modelAndViewResponse.setViewName("student/courseInfo");
                    modelAndViewResponse.addObject("course", course);
                    modelAndViewResponse.addObject("courseInstructor", courseInstructor);
                    modelAndViewResponse.addObject("user", user);
                } else {
                    modelAndViewResponse = new ModelAndView("redirect:/login");
                }
            } else {
                modelAndViewResponse = new ModelAndView("redirect:/login");
            }
        } else {
            modelAndViewResponse = new ModelAndView("redirect:/login");
        }
        return modelAndViewResponse;
    }
}
