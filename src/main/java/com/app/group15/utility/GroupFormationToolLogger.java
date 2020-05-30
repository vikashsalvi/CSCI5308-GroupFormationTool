package com.app.group15.utility;

import org.apache.log4j.Logger;

import java.util.logging.Level;

public class GroupFormationToolLogger {

    private static Logger logger;
    String logLevel = "INFO";
    String logPath = "logs/";

    public GroupFormationToolLogger() {
        /*Handler logFileHandler;
        Formatter plainText;
        try {
            //FileUtility.createFileIfNotExist(logPath);
            if (!Files.exists(Paths.get(logPath))) {
                Files.createDirectories(Paths.get(logPath));
            }
            logger = Logger.getLogger(GroupFormationToolLogger.class.getName());
            LocalDate date = LocalDate.now();
            String logFileName = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(date);
            logFileHandler = new FileHandler(logPath + logFileName + ".log", true);
            logFileHandler.setLevel(Level.parse(logLevel));
            plainText = new SimpleFormatter();
            logFileHandler.setFormatter(plainText);
            logger.addHandler(logFileHandler);
        }
        catch (Exception e){
            e.printStackTrace();
        }*/
        logger = Logger.getLogger(GroupFormationToolLogger.class);
    }

    private static Logger getLogger() {
        return logger == null ? new GroupFormationToolLogger().getLogger() : logger;
    }

    public static void log(Level level, String msg, Exception e) {
        getLogger().info(e);
    }
}
