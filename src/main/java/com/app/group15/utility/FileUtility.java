package com.app.group15.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtility {
    public static boolean checkFileExist(String path) {
        if(Files.exists(Paths.get(path))){
            return true;
        }else{
            return false;
        }
    }

    public static void createDirectory(String path) {
        try {
            Files.createDirectories(Paths.get(path));
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public static void createFileIfNotExist(String path) {
        if (checkFileExist(path)) { createDirectory(path); }
    }
}
