package com.app.group15.Utility;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FileUtilityTest {

    @Test
    public void checkFileExistTest() {
        String propertyFilePath = "src/main/resources/application.properties";
        assertEquals(FileUtility.checkFileExist(propertyFilePath), true);
    }
}
