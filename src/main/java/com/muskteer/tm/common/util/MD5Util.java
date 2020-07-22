package com.muskteer.tm.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class MD5Util {
    /**
     * @param args
     * @throws IOException
     */
    public static String MD5encript(String val) {

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            try {
                byte[] content = digest.digest(val.getBytes("UTF-8"));
                return new String(Base64Util.encode(content));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
}

