package com.kawahedukasi.util;

public class BasicUtil {
    //roundUp pagination
    public static long roundUp(long num, long divisor) {
        return (num + divisor - 1) / divisor;
    }
}
