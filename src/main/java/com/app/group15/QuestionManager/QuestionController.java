package com.app.group15.QuestionManager;

import com.app.group15.Config.ServiceConfig;
import com.app.group15.UserManagement.SessionManagement.IAuthorizationService;
import com.app.group15.UserManagement.SessionManagement.ISessionService;
import com.app.group15.UserManagement.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {

	IQuestionManagerService questionManagerService = ServiceConfig.getInstance().getQuestionManagerService();
	private IAuthorizationService authorizationService = ServiceConfig.getInstance().getAuthorizationService();
	private ISessionService sessionService = ServiceConfig.getInstance().getSessionService();
	private IQuestionChoiceMapperService questionChoiceMapperService = ServiceConfig.getInstance().getQuestionChoiceMapperService();


	@RequestMapping(value = "/instructor/questions", method = RequestMethod.GET)
	public ModelAndView questionPage(HttpServletRequest request, @RequestParam(required = false, value = "sortColumn") String sortColumn) {

		authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
		ModelAndView modelAndView;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = sessionService.getSessionUser(request);
				if (sortColumn == null) {
					sortColumn = "questionId";
				}
				ArrayList<Question> questionsList = (ArrayList<Question>) questionManagerService.getAllQuestionsOfInstructor(user.getId(), sortColumn);
				modelAndView = new ModelAndView();
				modelAndView.addObject("userEntity", user);
				modelAndView.addObject("questionsList", questionsList);
				modelAndView.setViewName("/question/question");
			} else {
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			modelAndView = new ModelAndView("redirect:/login");
		}

		return modelAndView;
	}


	@RequestMapping(value = "/instructor/viewQuestion", method = RequestMethod.GET)
	public ModelAndView questionPage(HttpServletRequest request,
									 @RequestParam(required = true, value = "questionId") int questionId) {
		authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
		ModelAndView modelAndView;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				modelAndView = new ModelAndView();

				modelAndView.addObject("viewOnly", true);

				User user = sessionService.getSessionUser(request);
				Question question = questionManagerService.getQuestion(questionId);
				modelAndView.addObject("userEntity", user);

				System.out.println(question.toString());

				List<String> options = questionManagerService.getOptions(questionId);

				System.out.println(options.toString());
				if (options != null) {
					modelAndView.addObject("options", options);
				}
				modelAndView.addObject("question", question);
				modelAndView.setViewName("/question/questionPreview");

			} else {
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			modelAndView = new ModelAndView("redirect:/login");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/instructor/questions/addQuestion", method = RequestMethod.GET)
	public ModelAndView addQuestions(HttpServletRequest request) {
		authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
		ModelAndView modelAndViewResponse;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				User user = sessionService.getSessionUser(request);
				List<QuestionType> allQuestionType = questionManagerService.getQuestionType();

				modelAndViewResponse = new ModelAndView();
				modelAndViewResponse.addObject("userEntity", user);
				modelAndViewResponse.addObject("question_type", allQuestionType);
				modelAndViewResponse.setViewName("/question/add_question");

			} else {
				modelAndViewResponse = new ModelAndView("redirect:/login");
			}
		} else {
			modelAndViewResponse = new ModelAndView("redirect:/login");
		}

		return modelAndViewResponse;
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
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
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
				model.addObject("viewOnly", false);
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
	public ModelAndView addRow(final Question question, final BindingResult bindingResult,
							   final ModelAndView model, HttpServletRequest request) {
		question.getOptions().add(new Options());
		User user = sessionService.getSessionUser(request);
		model.addObject("userEntity", user);
		model.setViewName("/question/questionManager");
		return model;
	}

	@RequestMapping(value = "/instructor/question/delete")
	public ModelAndView deleteQuestion(HttpServletRequest request,
									   @RequestParam(required = true, value = "questionId") int questionId) {
		authorizationService.setAllowedRoles(new String[]{"INSTRUCTOR"});
		ModelAndView modelAndView;
		if (sessionService.isUserSignedIn(request)) {
			if (authorizationService.isAuthorized(request)) {
				questionChoiceMapperService.deleteByQuestionId(questionId);
				modelAndView = new ModelAndView("redirect:/instructor/questions");
			} else {
				modelAndView = new ModelAndView("redirect:/login");
			}
		} else {
			modelAndView = new ModelAndView("redirect:/login");
		}

		return modelAndView;
	}

}
