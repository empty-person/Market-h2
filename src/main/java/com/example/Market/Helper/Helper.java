package com.example.Market.Helper;


public class Helper {


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String getAuthenticatedUserLogin() {

        return "username1";
    }

    public static Double getPriceAsNumeric(String str) {
        str = str.replace("$", "");
        if (isNumeric(str)) {
            return Double.parseDouble(str);
        } else return null;

    }
}
