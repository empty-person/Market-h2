package com.example.Market.Helper;

import org.springframework.security.core.context.SecurityContextHolder;

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

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static Double getPriceAsNumeric(String str) {
        str = str.replace("$", "");
        if (isNumeric(str)) {
            return Double.parseDouble(str);
        } else return null;

    }
}
