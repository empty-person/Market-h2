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

    public static Double getNumeric(String strNum) {

        return Double.parseDouble(strNum);
    }

    public static boolean isStringContainsNumbers(String str) {
        if (str != null && !str.isEmpty()) {
            for (char c : str.toCharArray()) {
                if (Character.isDigit(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getDefaultGeneratedUserLogin() {

        return "username1";
    }

    public static Long CastObjectToLong(Object object) throws Exception {
        Long result = 0l;

            if (object instanceof Long){
                result = ((Long) object).longValue();
            }
            else if (object instanceof Integer) {
                result = ((Integer) object).longValue();
            } else if (object instanceof String) {
                result = Long.valueOf((String) object);
            }else {
                throw new Exception();
            }

        return result;
    }

    public static Double getPriceAsNumeric(String str) {
        str = str.replace("$", "");
        if (isNumeric(str)) {
            return Double.parseDouble(str);
        } else return null;

    }
}
