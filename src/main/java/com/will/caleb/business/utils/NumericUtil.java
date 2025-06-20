package com.will.caleb.business.utils;

public class NumericUtil {

    public static boolean isEquals(Number a, Number b) {
        return a.doubleValue() == b.doubleValue();
    }

    public static boolean isGreater(Number a, Number b) {
        return a.doubleValue() > b.doubleValue();
    }

    public static boolean isLess(Number a, Number b) {
        return a.doubleValue() < b.doubleValue();
    }

}
