DELIMITER $$
DROP PROCEDURE IF EXISTS spSaveSurvey $$
CREATE PROCEDURE `spSaveSurvey`( IN courseId BIGINT)
BEGIN

    INSERT INTO `table_survey` (`course_id`,`publish_state`) VALUES (courseId, 0);

END$$
DELIMITER ;
