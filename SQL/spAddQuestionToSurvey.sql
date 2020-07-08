CREATE DEFINER=`CSCI5308_15_DEVINT_USER`@`%` PROCEDURE `spAddQuestionToSurvey`(
IN questionId BIGINT,
IN questionOrder BIGINT,
IN surveyId BIGINT,
IN rule_id BIGINT,
OUT mapperId BIGINT)
BEGIN
	INSERT INTO `table_survey_questions_mapper` (`question_id`,`questionOrder`,`survey_id`,`rule_id`) VALUES (questionId, questionOrder, surveyId, rule_id);
    SET mapperId=last_insert_id();
END
