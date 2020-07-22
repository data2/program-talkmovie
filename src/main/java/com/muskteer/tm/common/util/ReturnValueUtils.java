package com.muskteer.tm.common.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

public class ReturnValueUtils {
    public static void returnAjaxString(HttpServletResponse response, String result) {
        response.setContentType("text/javascript;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    public static void returnAjaxStringCallback(HttpServletResponse response, String callback, String result) {
        response.setContentType("text/javascript;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(callback + "(" + result + ")");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    public static String returnTalkmovieAjaxString(String status, String errmsg, Object obj) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("status", status);
        map.put("errmsg", errmsg);
        map.put("reqParams", obj == null ? "" : obj.toString());
        return JSON.toJSONString(map);
    }

    public static String objectToJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static String arrayToJson(Object obj) {
        return JSON.toJSONString(obj);
    }
}
