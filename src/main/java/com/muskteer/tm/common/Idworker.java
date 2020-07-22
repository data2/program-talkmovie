/**
 * FileName:   Idworker.java
 *
 * @Description TODO
 * All rights Reserved, Code by Muskteer
 * Copyright MuskteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.tm.common;

/**
 * @author wanglei
 *
 */
public class Idworker {
    private static long workerId = 968;
    private static long workerIdBits = 10;
    private static long sequenceBits = 11;
    private static long epoch = 1387886498127L; // 2013-12-24 20:01:38.127
    private static long workerIdShift = 11;


    public static long nextId() {
        long curr = System.currentTimeMillis();
        long diff = curr - epoch;
        long timestampLeftShift = workerIdBits + sequenceBits;
        return (diff << timestampLeftShift) |
                (workerId << workerIdShift) |
                0;
    }
}
