package com.muskteer.tm.common.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie set and get utilities.
 *
 * @author BingooHuang
 */
public abstract class CookieUtils {
    /**
     * Deactive when explorer closed.
     */
    private static final int DEFAULT_AGE = -1;

    /**
     * Sets the cookie
     *
     * @param response
     * @param cookieName
     * @param value
     */
    public static void setCookie(HttpServletResponse response,
                                 String cookieName, String value) {
        setCookie(response, cookieName, value, DEFAULT_AGE);
    }

    /**
     * 按照SUN J2EE DOC的解释，如果setMaxAge为正值，
     * 则Cookie在N秒之后过期，如果为零，则删除之，如果为负值，则在浏览期关闭之后被删除。
     *
     * @param response
     * @param cookieName
     * @param value
     * @param maxSeconds
     */
    public static void setCookie(HttpServletResponse response,
                                 String cookieName, String value, int maxSeconds) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(maxSeconds);

        cookie.setDomain("");
        cookie.setPath("/");

        response.addCookie(cookie);
    }

    /**
     * Returns the value of the cookie specified by cookieName or defaultValue
     * if Cookie does not exist.
     *
     * @param request
     * @param cookieName
     * @param defaultValue
     * @return
     */
    public static String getCookieValue(HttpServletRequest request,
                                        String cookieName, String defaultValue) {
        Cookie cookie = getCookie(request, cookieName);

        return cookie == null ? defaultValue : cookie.getValue();
    }

    /**
     * Return cookie Object specified by cookieName or null if cookie does not
     * exist.
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        System.out.println(request.getHeader("Cookie"));
        String cookiestamp = request.getHeader("Cookie");

        if (StringUtil.isNotEmpty(cookiestamp)) {
            String[] args = cookiestamp.split(";");
            for (String cookie : args) {
                int index = cookie.indexOf("=");
                if (index == -1) {
                    continue;
                } else {//header中取
                    String cookie_key = cookie.substring(0, index);
                    if (StringUtil.isNotEmpty(cookie_key)
                            && cookie_key.equals(cookieName)) {
                        System.out.println("header get cookie");
                        return new Cookie(cookieName, cookie.substring(index + 1));
                    } else {
                        //header中没有，从request.getcookies取
                        System.out.println("cookies get cookie");
                        Cookie[] cookies = request.getCookies();
                        if (cookies != null) {
                            for (Cookie request_cookie : cookies) {
                                System.out.println(request_cookie.getValue());
                                if (cookieName.equals(request_cookie.getName())) {
                                    System.out.println(request_cookie.getValue());
                                    return request_cookie;
                                }
                            }
                        }


                    }


                }
            }
        }
        return null;
    }


    /**
     * 设置登录cookie
     *
     * @param tokenValue
     * @param cookieJoke
     * @param
     * @return
     */
    public static void setLoginCookie(HttpServletResponse response, String nickname, String tokenValue,
                                      String cookieJoke) {
        String encrptKey = PropertiesUtils.getProperty("encrptKey");
        CookieUtils.setCookie(response, "TOKEN", nickname + "|"
                        + AESUtil3.encrypt(tokenValue, encrptKey) + "|" + cookieJoke,
                60 * 30);
    }

    /**
     * @param request
     * @param string
     * @return
     */
    public static String getCookieParam(HttpServletRequest request, String string) {
        try {

            if (StringUtil.isNotEmpty(request.getParameter(string))) {
                if (StringUtils.equals("TOKEN", string)) {
                    String s = request.getParameter(string);
                    System.out.println("cookie from TOKEN : " + request.getParameter(string));
                    return s.split("\\|")[0] + "|" + s.split("\\|")[1];
                } else {
                    return request.getParameter(string);
                }
            }
            for (Cookie request_cookie : request.getCookies()) {
                System.out.println(request_cookie.getValue());
                if (string.equals(request_cookie.getName())) {
                    System.out.println("cookie form cookies : " + request_cookie.getValue());
                    if (StringUtils.equals("TOKEN", string)) {
                        return request_cookie.getValue() + "==";
                    } else {
                        return request_cookie.getValue();
                    }
                }
            }
            System.out.println("cookie not found");


        } catch (Exception e) {

        }
        return null;
    }

}
