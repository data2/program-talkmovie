package com.muskteer.tm.common.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

/**
 * 通过AES算法对文本进行加密解密
 *
 * @author ShaoJiang
 */
public class AESUtil3 {
    private static byte[] keyValue = new byte[]{ // 用户密钥 +
            22, 25, -35, -45, 25, 98, -55, -45, 10, 35, -45, 25, 26, -95, 25, -65, -78,
            -99, 85, 45, -62, 10, -0, 11, -35, 48, -98, 65, -32, 14, -78, 25,
            36, -56, -45, -45, 12, 15, -35, -75, 15, -14, 62, -25, 33, -45, 55,
            68, -88};
    private static byte[] iv = new byte[]{ // 算法参数
            -12, 35, -25, 65, 45, -87, 95, -22, -15, 45, 55, -66, 32, 5 - 4, 84, 55};
    private static SecretKey key; // 加密密钥
    private static AlgorithmParameterSpec paramSpec; // 算法参数
    private static Cipher ecipher; // 加密算法

    static {
        KeyGenerator kgen;
        try {
            // 为指定算法生成一个密钥生成器对象。
            kgen = KeyGenerator.getInstance("AES");
            // 使用用户提供的随机源初始化此密钥生成器，使其具有确定的密钥长度。
            kgen.init(128, new SecureRandom(keyValue));
            // 使用KeyGenerator生成（对称）密钥。
            key = kgen.generateKey();
            // 使用iv中的字节作为IV来构造一个 算法参数。
            paramSpec = new IvParameterSpec(iv);
            // 生成一个实现指定转换的 Cipher 对象
            ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param msg 要加密消息
     * @return 加密后转成16进制字符串
     */
    public static String encrypt(String msg) {
        String str = "";
        try {
            // 用密钥和一组算法（向量）参数初始化此 cipher
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            // 加密并转换成16进制字符串
            str = HexUtil.ByteArr2HexStr(ecipher.doFinal(msg.getBytes()));
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * @param msg    要加密消息
     * @param strKey 加密密钥
     * @return 加密后再用base64编码
     */
    public static String encrypt(String msg, String strKey) {
        SecretKeySpec skeySpec;
        try {
            skeySpec = getKey(strKey);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(AESUtil3.iv);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(msg.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param value 要解密消息
     * @return 加密时返回16进制字符串的 解密
     */
    public static String decrypt(String value) {
        try {
            ecipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
            return new String(ecipher.doFinal(HexUtil.HexStr2ByteArray(value)));
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param value 要解密消息
     * @param key   密钥
     * @return 加密时返回base64编码的 解密
     */
    public static String decrypt(String value, String key) {
        SecretKeySpec skeySpec;
        try {
            skeySpec = getKey(key);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(AESUtil3.iv);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.decodeBase64(value);

            byte[] original = cipher.doFinal(encrypted1);
            return new String(original);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static SecretKeySpec getKey(String strKey) throws Exception {
        byte[] arrBTmp = strKey.getBytes();
        byte[] arrB = new byte[16]; // 创建一个空的16位字节数组（默认值为0）

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");

        return skeySpec;
    }

    public static void main(String[] args) {
        String s = encrypt("wangleiaini", "1231");
        System.out.println(s);
        System.out.println(decrypt(s, "1231"));
        System.out.println();
    }
}