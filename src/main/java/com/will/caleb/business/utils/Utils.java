package com.will.caleb.business.utils;

import org.springframework.util.ObjectUtils;

public class Utils {

    public static boolean isEmpty(Object object) {
        return ObjectUtils.isEmpty(object) || object == null;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    @SuppressWarnings("unchecked")
    public static <T> T nvl(Object change, T changeTo) {
        if (isNotEmpty(change)) {
            return (T) change;
        }
        return changeTo;
    }

    public static boolean equals(Object object, Object compareTo) {
        return object.equals(compareTo);
    }

}
