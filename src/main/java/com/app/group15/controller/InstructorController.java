package com.app.group15.controller;

import com.app.group15.dao.CourseDao;
import com.app.group15.injectors.CourseDaoInjectorService;
import com.app.group15.model.Course;
import com.app.group15.model.User;
import com.app.group15.services.*;
import com.app.group15.utility.GroupFormationToolLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.logging.Level;

@Controller
public class InstructorController {
	private AuthorizationService authorizationService = new AuthorizationService();
	private CourseDao courseDao = new CourseDaoInjectorService().getCourseDao();

	@RequestMapping(value = "/instructor/home", method = RequestMethod.GET)
	public ModelAndView adminHome(HttpServletRequest request) {
		authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {

				InstructorService instructorService = new InstructorService();

				User userEntity = SessionService.getSessionUser(request);
				ArrayList<Course> courseEntities = instructorService.getCourseOfInstructor((userEntity.getId()));
				ArrayList<User> userEntitiesTA = InstructorService.getAllCourseTA(courseEntities);

				modelAndView = new ModelAndView();
				modelAndView.setViewName("instructor/home");
				modelAndView.addObject("userEntity", userEntity);
				modelAndView.addObject("courseEntities", courseEntities);
				modelAndView.addObject("userEntitiesTA", userEntitiesTA);

				return modelAndView;
			} else {
				//Unauthorized access for /instructor/home
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			// User not logged in !!!
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/instructor/assign-ta", method = RequestMethod.GET)
	public ModelAndView assignTAGET(HttpServletRequest request, @RequestParam String courseId) {
		authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {

				User userEntity = SessionService.getSessionUser(request);
				CourseDao courseDao = new CourseDaoInjectorService().getCourseDao();
				Course courseEntity = courseDao.get(Integer.parseInt(courseId));

				modelAndView = new ModelAndView();
				modelAndView.setViewName("instructor/ta-assignment");
				modelAndView.addObject("courseId", courseId);
				modelAndView.addObject("userEntity", userEntity);
				modelAndView.addObject("courseEntity", courseEntity);

				return modelAndView;
			} else {
				//Unauthorized access for instructor/assign-ta
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			// User not logged in
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}


	@RequestMapping(value = "/instructor/assign-ta", method = RequestMethod.POST)
	public ModelAndView assignTAPOST(HttpServletRequest request, @RequestParam(required = true, value = "bannerId") String bannerId, @RequestParam(required = true, value = "courseId") int courseId) {
		authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
		ModelAndView modelAndView;
		AssignTAService assignTAService = new AssignTAService();
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {


				User userEntity = SessionService.getSessionUser(request);
				InstructorService instructorService = new InstructorService();
				ArrayList<Course> courseEntities = instructorService.getCourseOfInstructor((userEntity.getId()));
				ArrayList<User> userEntitiesTA = InstructorService.getAllCourseTA(courseEntities);
//				CourseDao courseDao = new CourseDaoInjectorService().getCourseDao();
				Course courseEntity = courseDao.get(courseId);


				modelAndView = new ModelAndView();

				// if instructor has no right to change the TA
				if (!assignTAService.checkIntructorPermission(userEntity.getId(), courseId)) {
					modelAndView.addObject("error_invalid_permission", true);
				}

				// performing change TA
				if (assignTAService.validateBannerID(bannerId)) {

					modelAndView.addObject("error", false);
					if (assignTAService.performTAUpdate(bannerId, courseId)) {
						System.out.println("Performing UPDATE");
						modelAndView.setViewName("redirect:/instructor/home");
						return modelAndView;
					}
				} else {

					modelAndView.addObject("error", true);
				}

				modelAndView.setViewName("instructor/ta-assignment");
				modelAndView.addObject("courseId", courseId);
				modelAndView.addObject("userEntity", userEntity);
				modelAndView.addObject("courseEntity", courseEntity);
				modelAndView.addObject("userEntitiesTA", userEntitiesTA);
				return modelAndView;

			} else {
				//Unauthorized access for instructor/assign-ta
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			// User not logged in !
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}


	@RequestMapping(value = "/instructor/importCSV", method = RequestMethod.GET)
	public ModelAndView importCSV(HttpServletRequest request, @RequestParam(required = true, value = "courseId") int courseId) {
		authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR", "TA"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = SessionService.getSessionUser(request);
				GroupFormationToolLogger.log(Level.INFO, "User authorized 147");
				if (CourseService.isUserCourseAdmin(courseId, user.getId())) {
					GroupFormationToolLogger.log(Level.INFO, "User Course admin");
					Course course = courseDao.get(courseId);
					modelAndView = new ModelAndView();
					modelAndView.setViewName("instructor/import_csv");
					modelAndView.addObject("user", user);
					modelAndView.addObject("course", course);
				} else {
					GroupFormationToolLogger.log(Level.INFO, "User Not Course admin");
					modelAndView = new ModelAndView();
					modelAndView.setViewName("redirect:/login");
				}
			} else {
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}

//	TODO error occuring when trying to upload file with single line. Ask Vikas or suraj for this. Also ask about how send error message on exception
	@RequestMapping(value = "/instructor/importCSV", method = RequestMethod.POST)
	public ModelAndView importCSV(HttpServletRequest request,
								  RedirectAttributes redirectAttributes,
								  @RequestParam(required = true, value = "courseId") int courseId,
								  @RequestParam(required = true, value = "csvFile") MultipartFile csvFile) {
		authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR", "TA"});
		ModelAndView modelAndView;
		if (SessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = SessionService.getSessionUser(request);
//				GroupFormationToolLogger.log(Level.INFO, "User authorized 147");
				if (CourseService.isUserCourseAdmin(courseId, user.getId())) {
					GroupFormationToolLogger.log(Level.INFO, "User authorized");
					int insertCount = InstructorService.addStudentsFromCSV(csvFile, courseId);
					if (insertCount==-1){
						redirectAttributes.addFlashAttribute("error",
						"Invalid file structure!");
						modelAndView=new ModelAndView("redirect:/instructor/importCSV");
					} else if(insertCount==0){
						redirectAttributes.addFlashAttribute("error",
							"No records to insert!");
						modelAndView=new ModelAndView("redirect:/instructor/importCSV");
					} else{
						modelAndView = new ModelAndView();
						redirectAttributes.addFlashAttribute("message",
							"File uploaded successfully!");
						modelAndView.setViewName("redirect:/instructor/home");
					}
				} else {
					GroupFormationToolLogger.log(Level.INFO, "User Not Course admin");
					modelAndView = new ModelAndView();
					modelAndView.setViewName("redirect:/login");
				}
			} else {
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			modelAndView = new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}
}
