DELIMITER $$

DROP PROCEDURE IF EXISTS spMapQuestionChoice $$

CREATE PROCEDURE spMapQuestionChoice (

    IN questionId BIGINT,
    IN choiceId BIGINT,
    OUT IN_ID BIGINT
    )

BEGIN
	INSERT
	INTO table_question_choice_mapper(question_id,choice_id)
	VALUES(questionId,choiceId);

    SET IN_ID = LAST_INSERT_ID();

END $$

DELIMITER ;