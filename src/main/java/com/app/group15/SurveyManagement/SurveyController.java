package com.app.group15.SurveyManagement;


import com.app.group15.Config.AppConfig;
import com.app.group15.Config.ServiceConfig;
import com.app.group15.CourseManagement.Course;
import com.app.group15.CourseManagement.CourseAbstractDao;
import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.QuestionManager.Question;
import com.app.group15.UserManagement.SessionManagement.IAuthorizationService;
import com.app.group15.UserManagement.SessionManagement.ISessionService;
import com.app.group15.UserManagement.User;
import com.app.group15.Utility.GroupFormationToolLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Controller
public class SurveyController {

    private ISurveyManagementAbstractFactory surveyManagementAbstractFactory = AppConfig.getInstance().getSurveyManagementAbstractFactory();
    private IAuthorizationService authorizationService = ServiceConfig.getInstance().getAuthorizationService();
    private CourseAbstractDao courseDao = AppConfig.getInstance().getCourseDao();
    private ISessionService sessionService = ServiceConfig.getInstance().getSessionService();
    private ISurveyService surveyService = surveyManagementAbstractFactory.getSurveyService();

    @RequestMapping(value = "/instructor/survey", method = RequestMethod.GET)
    public ModelAndView manageSurveyGET(HttpServletRequest request,
                                        @RequestParam(value = "courseId", required = true) String courseId,
                                        @RequestParam(value = "show", required = false) String showField) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ArrayList<Question> allQuestionsOfInstructor = null;
        List<Question> questionList = new ArrayList<>();
        ModelAndView modelAndView;
        Survey survey;
        boolean showLt = false, showGt = false;
        try {
            if (sessionService.isUserSignedIn(request)) {
                if (authorizationService.isAuthorized(request)) {
                    User user = sessionService.getSessionUser(request);
                    try {
                        allQuestionsOfInstructor = (ArrayList<Question>) surveyService.getRemainingQuestionsForSurvey(Integer.parseInt(courseId), user.getId());
                    } catch (Exception e) {
                        GroupFormationToolLogger.log(Level.INFO, "Exception while all questions of instructor", e);
                    }
                    Course courseEntity = (Course) courseDao.get(Integer.parseInt(courseId));
                    try {
                        questionList = surveyService.getSurveyQuestionByCourseID(courseEntity.getId());
                    } catch (Exception e) {
                        GroupFormationToolLogger.log(Level.INFO, "Exception while getting Questions", e);
                    }
                    modelAndView = new ModelAndView();
                    if (showField != null && showField.equals("lt")) {
                        showLt = true;
                    } else if (showField != null && showField.equals("gt")) {
                        showGt = true;
                    } else {
                        showLt = false;
                        showGt = false;
                    }
                    survey = surveyService.getSurveyByCourseId(Integer.parseInt(courseId));
                    modelAndView.setViewName("instructor/survey");
                    modelAndView.addObject("survey", survey);
                    modelAndView.addObject("allQuestionsOfInstructor", allQuestionsOfInstructor);
                    modelAndView.addObject("courseId", courseId);
                    modelAndView.addObject("userEntity", user);
                    modelAndView.addObject("courseEntity", courseEntity);
                    modelAndView.addObject("questionList", questionList);
                    modelAndView.addObject("showLt", showLt);
                    modelAndView.addObject("showGt", showGt);

                    return modelAndView;
                } else {
                    modelAndView = new ModelAndView("redirect:/login");
                }
            } else {
                modelAndView = new ModelAndView("redirect:/login");
            }
            return modelAndView;
        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.INFO, " Redirecting to /dbError endpoint ");
            modelAndView = new ModelAndView("dbError");
            return modelAndView;
        } catch (AwsSecretsManagerException e) {
            GroupFormationToolLogger.log(Level.INFO, " Redirecting to /awsError endpoint ");
            modelAndView = new ModelAndView("awsError");
            return modelAndView;
        }
    }

    @RequestMapping(value = "instructor/survey/addQuestion", method = RequestMethod.POST)
    public ModelAndView manageSurvey(HttpServletRequest request,
                                     @RequestParam(value = "courseId", required = true) int courseId,
                                     @RequestParam(value = "questionId", required = true) int questionId,
                                     @RequestParam(value = "rule", required = true) String rule,
                                     @RequestParam(value = "ruleValue", required = false) String ruleVal
    ) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        try {
            if (sessionService.isUserSignedIn(request)) {
                if (authorizationService.isAuthorized(request)) {
                    try {
                        int ruleValue;
                        if (ruleVal != null) {
                            ruleValue = Integer.parseInt(ruleVal);
                        } else {
                            ruleValue = 0;
                        }
                        int mapperId = surveyService.addQuestionToSurvey(courseId, questionId, rule, ruleValue);
                        GroupFormationToolLogger.log(Level.FINEST, String.format("Question added to survey with id %d", mapperId));
                    } catch (Exception e) {
                        GroupFormationToolLogger.log(Level.INFO, "Exception while adding question to survey", e);
                    }
                    modelAndView = new ModelAndView(String.format("redirect:/instructor/survey?courseId=%d", courseId));
                    return modelAndView;
                } else {
                    modelAndView = new ModelAndView("redirect:/login");
                }
            } else {
                modelAndView = new ModelAndView("redirect:/login");
            }
            return modelAndView;
        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.INFO, " Redirecting to /dbError endpoint ");
            modelAndView = new ModelAndView("dbError");
            return modelAndView;
        } catch (AwsSecretsManagerException e) {
            GroupFormationToolLogger.log(Level.INFO, " Redirecting to /awsError endpoint ");
            modelAndView = new ModelAndView("awsError");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/instructor/survey/deleteQuestion", method = RequestMethod.POST)
    public ModelAndView deleteQuestion(HttpServletRequest request,
                                       @RequestParam(value = "courseId", required = true) int courseId,
                                       @RequestParam(value = "questionId", required = true) int questionId) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        try {
            if (sessionService.isUserSignedIn(request)) {
                if (authorizationService.isAuthorized(request)) {
                    try {
                        surveyService.deleteSurveyQuestion(questionId, courseId);
                        GroupFormationToolLogger.log(Level.FINEST, String.format("Question with id %d deleted from survey with id", questionId));
                    } catch (Exception e) {
                        GroupFormationToolLogger.log(Level.INFO, "Exception while adding question to survey", e);
                    }
                    modelAndView = new ModelAndView(String.format("redirect:/instructor/survey?courseId=%d", courseId));
                    return modelAndView;
                } else {
                    modelAndView = new ModelAndView("redirect:/login");
                }
            } else {
                modelAndView = new ModelAndView("redirect:/login");
            }
            return modelAndView;
        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.INFO, " Redirecting to /dbError endpoint ");
            modelAndView = new ModelAndView("dbError");
            return modelAndView;
        } catch (AwsSecretsManagerException e) {
            GroupFormationToolLogger.log(Level.INFO, " Redirecting to /awsError endpoint ");
            modelAndView = new ModelAndView("awsError");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/instructor/survey/publish", method = RequestMethod.POST)
    public ModelAndView publishSurvey(HttpServletRequest request,
                                      @RequestParam(value = "courseId", required = true) int courseId) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        try {
            if (sessionService.isUserSignedIn(request)) {
                if (authorizationService.isAuthorized(request)) {
                    try {
                        surveyService.publishSurvey(courseId);
                        GroupFormationToolLogger.log(Level.FINEST, String.format("Survey of course with course id %d published!", courseId));
                    } catch (Exception e) {
                        GroupFormationToolLogger.log(Level.INFO, "Exception while adding question to survey", e);
                    }
                    modelAndView = new ModelAndView(String.format("redirect:/instructor/survey?courseId=%d", courseId));
                    return modelAndView;
                } else {
                    modelAndView = new ModelAndView("redirect:/login");
                }
            } else {
                modelAndView = new ModelAndView("redirect:/login");
            }
            return modelAndView;
        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.INFO, " Redirecting to /dbError endpoint ");
            modelAndView = new ModelAndView("dbError");
            return modelAndView;
        } catch (AwsSecretsManagerException e) {
            GroupFormationToolLogger.log(Level.INFO, " Redirecting to /awsError endpoint ");
            modelAndView = new ModelAndView("awsError");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/instructor/survey/unPublish", method = RequestMethod.POST)
    public ModelAndView unPublishSurvey(HttpServletRequest request,
                                        @RequestParam(value = "courseId", required = true) int courseId) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        try {
            if (sessionService.isUserSignedIn(request)) {
                if (authorizationService.isAuthorized(request)) {
                    try {
                        surveyService.unPublishSurvey(courseId);
                        GroupFormationToolLogger.log(Level.FINEST, String.format("Survey of course with course id %d published!", courseId));
                    } catch (Exception e) {
                        GroupFormationToolLogger.log(Level.INFO, "Exception while adding question to survey", e);
                    }
                    modelAndView = new ModelAndView(String.format("redirect:/instructor/survey?courseId=%d", courseId));
                    return modelAndView;
                } else {
                    modelAndView = new ModelAndView("redirect:/login");
                }
            } else {
                modelAndView = new ModelAndView("redirect:/login");
            }
            return modelAndView;
        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.INFO, " Redirecting to /dbError endpoint ");
            modelAndView = new ModelAndView("dbError");
            return modelAndView;
        } catch (AwsSecretsManagerException e) {
            GroupFormationToolLogger.log(Level.INFO, " Redirecting to /awsError endpoint ");
            modelAndView = new ModelAndView("awsError");
            return modelAndView;
        }
    }

}
