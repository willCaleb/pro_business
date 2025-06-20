package com.will.caleb.business.utils;

import com.will.caleb.business.exception.CustomException;
import com.will.caleb.business.exception.EnumCustomException;
import org.springframework.util.ObjectUtils;

public class Utils {

    public static boolean isEmpty(Object object) {
        return ObjectUtils.isEmpty(object) || object == null;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    @SuppressWarnings("unchecked")
    public <T>T nvl(T objectToSet, Object returnIfNull) {

        if(equals(objectToSet.getClass(), returnIfNull.getClass())) {
            throw new CustomException(EnumCustomException.OBJECTS_ARE_DIFFERENTS);
        }

        if (Utils.isEmpty(objectToSet) || objectToSet == null) {
            return (T)returnIfNull;
        }
        return objectToSet;
    }

    public static boolean equals(Object object, Object compareTo) {
        return object.equals(compareTo);
    }

}
