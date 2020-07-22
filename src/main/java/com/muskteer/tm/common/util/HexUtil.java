package com.muskteer.tm.common.util;

public class HexUtil {
    /**
     * 将字节数组转换成16进制字符串
     *
     * @param buf
     * @return
     */
    public static String ByteArr2HexStr(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10)// 小于十前面补零
                strbuf.append("0");
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }

    /**
     * 将16进制字符串转换成字节数组
     *
     * @param src
     * @return
     */
    public static byte[] HexStr2ByteArray(String src) {
        if (src.length() < 1)
            return null;
        byte[] encrypted = new byte[src.length() / 2];
        for (int i = 0; i < src.length() / 2; i++) {
            int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);// 取高位字节
            int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);// 取低位字节
            encrypted[i] = (byte) (high * 16 + low);
        }
        return encrypted;
    }
}
