package com.app.group15.QuestionManager;

import com.app.group15.userManagement.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class QuestionManagerService implements IQuestionManagerInjectorService, IQuestionManagerService {

    private QuestionManagerAbstractDao questionManagerDao;

    @Override
    public void injectQuestionManagerInjectorService(QuestionManagerDao questionManagerDao) {
        this.questionManagerDao = questionManagerDao;
    }

    @Override
    public List<QuestionType> getQuestionType() {
        List<QuestionType> listOfQuestion = questionManagerDao.getAllQuestionTypes();
        listOfQuestion.forEach(questionType -> {
            questionType.setQuestionType(
                    QuestionTypeEnum.valueOf(questionType.getQuestionType()).getQuestionType());
        });
        return listOfQuestion;
    }

    @Override
    public boolean addQuestion(String questionTitle, String questionText, int questionTypeId) {
        Question question = new Question();
        return false;
    }

    @Override
    public Question formQuestion(String questionTitle, String questionText, int selectedOption) {
        Question question = new Question();
        question.setQuestionTitle(questionTitle);
        question.setQuestionText(questionText);
        question.setQuestionTypeId(selectedOption);
        if (selectedOption == 2 || selectedOption == 3) {
            question.addOptions(new Options());
        }
        return question;
    }

    @Override
    public List<String> formPreview(Question question) {
        List<String> options = null;
        if ((question.getQuestionTypeId() == 2) || (question.getQuestionTypeId() == 3)) {
            options = new ArrayList<>();
            List<String> temp = options;
            question.getOptions().forEach(opt -> {
                temp.add(opt.getOption());
            });
        }
        return options;
    }

    @Override
    public boolean insertQuestion(Question question, User user) {

        question.setQuestionInstructorId(user.getId());
        java.util.Date currentDate = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        question.setQuestionAddedDate(simpleDateFormat.format(currentDate));

        int insertedQuestionId = questionManagerDao.saveQuestion(question);
        if (question.getQuestionTypeId() == 2 || question.getQuestionTypeId() == 3) {
            if (insertedQuestionId != -1) {
                List<Options> choices = question.getOptions();
                List<Integer> choiceIdList = new ArrayList<>();
                for (Options choice : choices) {
                    int choiceID = questionManagerDao.saveChocie(choice);
                    choiceIdList.add(choiceID);
                }

                choiceIdList.forEach(insertedChoiceId -> {
                    questionManagerDao.saveQuestionChoiceMapping(insertedQuestionId, insertedChoiceId);
                });

            }
        }

        return false;
    }


}
