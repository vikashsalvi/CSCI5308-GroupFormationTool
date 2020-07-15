DELIMITER $$
DROP PROCEDURE IF EXISTS spGetUser $$
CREATE PROCEDURE `spGetUser`( IN studentId BIGINT)
BEGIN
	SELECT *
    	FROM table_users
    	WHERE id = studentId;
END$$
DELIMITER ;