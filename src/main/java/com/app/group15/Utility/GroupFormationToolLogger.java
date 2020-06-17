package com.app.group15.Utility;

import org.springframework.boot.logging.java.SimpleFormatter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

public class GroupFormationToolLogger {

    private static Logger logger;
    String logLevel = "INFO";
    String logPath = "logs/";

    public GroupFormationToolLogger() {
      Handler logFileHandler;
       Formatter plainText;
       try {
            if (!Files.exists(Paths.get(logPath))) {
                Files.createDirectories(Paths.get(logPath));
           }
            logger = Logger.getLogger(GroupFormationToolLogger.class.getName());
            LocalDate date = LocalDate.now();
           String logFileName = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(date);
            logFileHandler = new FileHandler(logPath + logFileName + ".log", true);            logFileHandler.setLevel(Level.parse(logLevel));
            plainText = new SimpleFormatter();
           logFileHandler.setFormatter(plainText);
            logger.addHandler(logFileHandler);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static Logger getLogger() {
        return logger == null ? new GroupFormationToolLogger().getLogger() : logger;
    }

    public static void log(Level level, String msg, Exception e) {
        getLogger().log(level, msg, e);
    }

    public static void log(Level level, String msg) {

		getLogger().log(level, msg);
	}

}
