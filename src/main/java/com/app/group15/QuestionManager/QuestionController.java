package com.app.group15.QuestionManager;

import com.app.group15.config.ServiceConfig;
import com.app.group15.courseManagement.ICourseService;
import com.app.group15.courseManagement.IInstructorService;
import com.app.group15.userManagement.IAuthorizationService;
import com.app.group15.userManagement.ISessionService;
import com.app.group15.userManagement.User;
import com.app.group15.utility.GroupFormationToolLogger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Level;

@Controller
public class QuestionController {

    IQuestionManagerService questionManagerService = ServiceConfig.getInstance().getQuestionManagerService();
    private IAuthorizationService authorizationService = ServiceConfig.getInstance().getAuthorizationService();
    private IInstructorService instructorService = ServiceConfig.getInstance().getInstructorService();
    private ICourseService courseService = ServiceConfig.getInstance().getCourseService();
    private ISessionService sessionService = ServiceConfig.getInstance().getSessionService();

    @RequestMapping(value = "/questions/addQuestion", method = RequestMethod.POST)
    public ModelAndView addQuestion(@RequestParam(required = true, value = "questionTitle") String questionTitle,
                                    @RequestParam(required = true, value = "questionText") String questionText,
                                    @RequestParam(required = true, value = "questionTypeId") int questionTypeId,
                                    HttpServletRequest request) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        if (sessionService.isUserSignedIn(request)) {
            if (authorizationService.isAuthorized(request)) {

            } else {
                //Unauthorized access
                modelAndView = new ModelAndView("redirect:/login");
            }
        } else {
            // User not logged in
            modelAndView = new ModelAndView("redirect:/login");
        }

        questionManagerService.addQuestion(questionTitle, questionText, questionTypeId);
        return null;
    }

    @RequestMapping(value = "/questions/fetchQuestionType", method = RequestMethod.POST)
    public ModelAndView fetchQuestionType(HttpServletRequest request) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        if (sessionService.isUserSignedIn(request)) {
            if (authorizationService.isAuthorized(request)) {
                List<QuestionType> questionTypeList = questionManagerService.getQuestionType();
                GroupFormationToolLogger.log(Level.INFO, questionTypeList.get(1).getQuestionType());
            } else {
                //Unauthorized access
                modelAndView = new ModelAndView("redirect:/login");
            }
        } else {
            // User not logged in
            modelAndView = new ModelAndView("redirect:/login");
        }

        return null;
    }

    @RequestMapping(value = "/instructor/questions", method = RequestMethod.GET)
    public ModelAndView questionPage(HttpServletRequest request) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        if (sessionService.isUserSignedIn(request)) {
            if (authorizationService.isAuthorized(request)) {
                User user = sessionService.getSessionUser(request);
                modelAndView = new ModelAndView();
                modelAndView.addObject("userEntity", user);
                modelAndView.setViewName("/question/question");

            } else {
                //Unauthorized access
                modelAndView = new ModelAndView("redirect:/login");
            }
        } else {
            // User not logged in
            modelAndView = new ModelAndView("redirect:/login");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/instructor/questions/addQuestion", method = RequestMethod.GET)
    public ModelAndView addQuestions(HttpServletRequest request) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        if (sessionService.isUserSignedIn(request)) {
            if (authorizationService.isAuthorized(request)) {
                User user = sessionService.getSessionUser(request);
                List<QuestionType> allQuestionType = questionManagerService.getQuestionType();

                modelAndView = new ModelAndView();
                modelAndView.addObject("userEntity", user);
                modelAndView.addObject("question_type", allQuestionType);
                modelAndView.setViewName("/question/add_question");

            } else {
                //Unauthorized access
                modelAndView = new ModelAndView("redirect:/login");
            }
        } else {
            // User not logged in
            modelAndView = new ModelAndView("redirect:/login");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/instructor/questions/add", method = RequestMethod.POST)
    public ModelAndView addQuestions(@RequestParam(required = false, value = "questionTitle") String questionTitle,
                                     @RequestParam(required = false, value = "question") String questionText,
                                     @RequestParam(required = false, value = "questionType") int selectedOption,
                                     HttpServletRequest request) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        ModelAndView modelAndView;
        if (sessionService.isUserSignedIn(request)) {
            if (authorizationService.isAuthorized(request)) {
                User user = sessionService.getSessionUser(request);

                modelAndView = new ModelAndView();
                Question question = questionManagerService.formQuestion(questionTitle, questionText, selectedOption);
                modelAndView.addObject("question", question);
                modelAndView.addObject("userEntity", user);
                modelAndView.setViewName("/question/questionManager");
                return modelAndView;

            } else {
                //Unauthorized access
                modelAndView = new ModelAndView("redirect:/login");
            }
        } else {
            // User not logged in
            modelAndView = new ModelAndView("redirect:/login");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/instructor/question/saveQuesAnsData", method = RequestMethod.POST)
    public ModelAndView saveQuestAnsData(@ModelAttribute Question question, ModelAndView model, HttpServletRequest request) {
        authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
        if (sessionService.isUserSignedIn(request)) {
            if (authorizationService.isAuthorized(request)) {

                model.addObject("response", "Question added successfully!!");

                User user = sessionService.getSessionUser(request);
                questionManagerService.insertQuestion(question, user);

                model.addObject("userEntity", user);
                List<String> options = questionManagerService.formPreview(question);
                if (options != null) {
                    model.addObject("options", options);
                }
                model.addObject("question", question);
                model.setViewName("/question/questionPreview");

            }
        }
        return model;
    }

    @RequestMapping(value = "/instructor/question/saveQuesAnsData", params = {"addRow"})
    public ModelAndView addRow(final Question question, final BindingResult bindingResult, final ModelAndView model, HttpServletRequest request) {
        question.getOptions().add(new Options());
        User user = sessionService.getSessionUser(request);
        model.addObject("userEntity", user);
        model.setViewName("/question/questionManager");
        return model;
    }

}
