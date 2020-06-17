package com.app.group15.Utility;


public class ServiceUtility {

    public static boolean isNotNull(String validateString) {
        if (null == validateString && validateString.length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isNotNull(Object validateObject) {
        if (null == validateObject) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidInt(int validateInt) {
        if (validateInt < 0) {
            return false;
        } else {
            return true;
        }
    }


}
