DELIMITER $$
DROP PROCEDURE IF EXISTS spGetChoiceValue $$
CREATE PROCEDURE `spGetChoiceValue`(

    IN choiceId BIGINT
    )
BEGIN
	SELECT * FROM
	table_choice
	where id = choiceId;

END$$
DELIMITER ;