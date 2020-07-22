package com.muskteer.tm.common.util;

import java.io.UnsupportedEncodingException;

/**
 * 字节操作相关小函数.
 *
 * @author Bingoo Huang
 */
public abstract class ByteUtils {
    /**
     * UTF-8编码名称。
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * 取得字符串UTF-8编码字节数组.
     *
     * @param str 字符串
     * @return UTF-8编码字节数组
     * @throws Exception
     */
    public static byte[] getUTF8Bytes(String str) throws Exception {
        return getBytes(str, UTF_8);
    }

    /**
     * 取得字符串编码字节数组, 按照指定编码方式。
     *
     * @param str     字符串
     * @param charset 编码方式
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] getBytes(String str, String charset) throws Exception {
        try {
            return str.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new Exception(e);
        }
    }

    /**
     * 生成UTF-8字符串.
     *
     * @param bytes 字节数组
     * @return 字符串
     * @throws Exception
     */
    public static String newUTF8String(byte[] bytes) throws Exception {
        return newString(bytes, UTF_8);
    }

    /**
     * 生成字符串, 按照指定编码方式.
     *
     * @param bytes   字节数组
     * @param charset 编码方式
     * @return 字符串
     * @throws Exception
     */
    public static String newString(byte[] bytes, String charset) throws Exception {
        try {
            return new String(bytes, charset);
        } catch (UnsupportedEncodingException e) {
            throw new Exception(e);
        }
    }

}