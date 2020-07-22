package com.muskteer.tm.common.util;

public final class StringUtil {
    public final static String CHARACTER = "*?/\\<>";

    public static boolean isValidString(String str) {
        if (str == null) {
            return false;
        }
        if (str.matches(".*[" + CHARACTER + "].*")) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isAllNotEmpty(String... str) {
        for (String s : str) {
            if (isEmpty(s)) {
                System.out.println("check " + s + " is null");
                return false;
            }
        }
        return true;
    }

    public static boolean isValidIdCard(String str) {
        return str == null ? false : str.matches("\\d{15}|\\d{17}[xX\\d]");
    }

    public static boolean isValidPhone(String str) {
        return str == null ? false : str.matches("\\d{11}|\\d{3,4}\\-\\d{7,8}");
    }

    public static boolean isValidNum(String str, int num) {
        if (num < 0) {
            return false;
        }
        return str == null ? false : str.matches("\\d{1," + num + "}");
    }

    public static boolean isValidPostCode(String str) {
        return str == null ? false : str.matches("\\d{6}");
    }

    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * @param str
     * @param maxlength 最高数位
     * @param minlength 小数以下的最小数位
     * @return 1：成功<br/>0：失败<br/>-1：参数异常<br/>
     */
    public static int isValidMoney(String str, int maxlength, int minlength) {
        if (minlength <= 0 || maxlength < 1) {
            return -1;
        }
        if (str != null && str.matches("\\d{1," + maxlength + "}(\\.\\d{1," + minlength + "})?")) {
            return 1;
        }
        return 0;
    }

    public static int isValidMoney(String str) {
        return isValidMoney(str, 6, 2);
    }

    /**
     * 中国联通：130.131.132.155.156.185.186.145
     *
     * @param str
     * @return
     */
    public static boolean isLianTongPhone(String str) {
        return str == null ? false : str.matches("1(45|3[0-2]|[58][56])[0-9]{8}");
    }

    public static boolean isEmail(String str) {
        return str == null ? false
                : str
                .matches("[a-zA-Z0-9_]+([-+.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([-.][a-zA-Z0-9_]+)*\\.[a-zA-Z0-9_]+([-.][a-zA-Z0-9_]+)*");
    }

    public static String getString(Object[] objs) {
        StringBuffer messages = new StringBuffer();
        for (Object obj : objs) {
            messages.append(obj.toString());
        }
        return messages.toString();
    }

    public static String replaceAll(String original, String target, String replacement) {
        StringBuffer result = new StringBuffer();
        int fromIndex = 0;
        do {
            int foundIndex = original.indexOf(target, fromIndex);
            if (foundIndex == -1) {
                result.append(original.substring(fromIndex));
                break;
            }
            result.append(original.substring(fromIndex, foundIndex));
            result.append(replacement);
            fromIndex = foundIndex + target.length();
        }
        while (true);
        return result.toString();
    }

    public static boolean isEqual(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        return false;
    }

}
