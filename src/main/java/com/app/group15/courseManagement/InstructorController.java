package com.app.group15.courseManagement;

import com.app.group15.config.AppConfig;
import com.app.group15.config.ServiceConfig;
import com.app.group15.userManagement.IAuthorizationService;
import com.app.group15.userManagement.ISessionService;
import com.app.group15.userManagement.User;
import com.app.group15.utility.GroupFormationToolLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Level;

@Controller
public class InstructorController {
	private IAuthorizationService authorizationService = ServiceConfig.getInstance().getAuthorizationService();
	private CourseAbstractDao courseDao = AppConfig.getInstance().getCourseDao();
	private ISessionService sessionService = ServiceConfig.getInstance().getSessionService();
	private ICourseService courseService = ServiceConfig.getInstance().getCourseService();
	private IInstructorService instructorService=ServiceConfig.getInstance().getInstructorService();
	private IAssignTAService assignTaService=ServiceConfig.getInstance().getAssignTaService();
	@RequestMapping(value = "/instructor/home", method = RequestMethod.GET)
	public ModelAndView adminHome(HttpServletRequest request) {
		authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
		System.out.println(authorizationService.getAllowedRoles().toString());
		ModelAndView modelAndView;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {

				User userEntity = sessionService.getSessionUser(request);
				List<Course> courseEntities = instructorService.getCourseOfInstructor((userEntity.getId()));
				List<User> userEntitiesTA = instructorService.getAllCourseTA(courseEntities);

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
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {

				User userEntity = sessionService.getSessionUser(request);
				Course courseEntity = (Course) courseDao.get(Integer.parseInt(courseId));

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
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {


				User userEntity = sessionService.getSessionUser(request);
				List<Course> courseEntities = instructorService.getCourseOfInstructor((userEntity.getId()));
				List<User> userEntitiesTA = instructorService.getAllCourseTA(courseEntities);
//				CourseDao courseDao = new CourseDaoInjectorService().getCourseDao();
				Course courseEntity = (Course) courseDao.get(courseId);


				modelAndView = new ModelAndView();

				// if instructor has no right to change the TA
				if (!assignTaService.checkIntructorPermission(userEntity.getId(), courseId)) {
					modelAndView.addObject("error_invalid_permission", true);
				} else {
					modelAndView.addObject("error_invalid_permission", false);
				}

				// performing change TA
				if (assignTaService.validateBannerID(bannerId)) {

					if (assignTaService.performTAUpdate(bannerId, courseId)) {
						modelAndView.addObject("error", false);
						System.out.println("Performing UPDATE");
						modelAndView.setViewName("redirect:/instructor/home");
						return modelAndView;
					}  else {
						modelAndView.addObject("error", true);
						modelAndView.addObject("errorMessage", String.format("Student with Banner ID \"%s\" is enrolled in your course!",bannerId));
					}
				} else {
					modelAndView.addObject("error", true);
					modelAndView.addObject("errorMessage", "Invalid Banner ID!");
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
		authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR", "TA", "STUDENT"});
		ModelAndView modelAndView;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = sessionService.getSessionUser(request);
				GroupFormationToolLogger.log(Level.INFO, "User authorized 147");
				if (courseService.isUserCourseAdmin(courseId, user.getId())) {
					GroupFormationToolLogger.log(Level.INFO, "User Course admin");
					Course course = (Course) courseDao.get(courseId);
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

	@RequestMapping(value = "/instructor/importCSV", method = RequestMethod.POST)
	public ModelAndView importCSV(HttpServletRequest request,
								  RedirectAttributes redirectAttributes,
								  @RequestParam(required = true, value = "courseId") int courseId,
								  @RequestParam(required = true, value = "csvFile") MultipartFile csvFile) {
		authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR", "TA"});
		ModelAndView modelAndView;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = sessionService.getSessionUser(request);
				if (courseService.isUserCourseAdmin(courseId, user.getId())) {
					GroupFormationToolLogger.log(Level.INFO, "User authorized");
					int insertCount = instructorService.addStudentsFromCSV(csvFile, courseId);
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
