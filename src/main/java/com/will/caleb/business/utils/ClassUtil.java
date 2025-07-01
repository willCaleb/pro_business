package com.will.caleb.business.utils;

import com.will.caleb.business.exception.CustomException;

import java.lang.reflect.Constructor;

public class ClassUtil {
    public static <T> T newInstance(Class<T> clazz) {
        try {

            Constructor<T> constructor = org.springframework.util.ClassUtils.getConstructorIfAvailable(clazz);

            if (Utils.isNotEmpty(constructor)) {
                return constructor.newInstance();
            } else {
                throw new CustomException("Não foi possível criar nova instância de " + clazz.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new CustomException("Não foi possível criar nova instância de ", clazz.getName());
    }

}
