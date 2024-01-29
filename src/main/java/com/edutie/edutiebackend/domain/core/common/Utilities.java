package com.edutie.edutiebackend.domain.core.common;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.Set;

public class Utilities {
    public static <T, U> Optional<Set<T>> findSetOf(Class<T> traitClass, U obj) {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.getType() == Set.class) {
                ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                Type[] typeArguments = genericType.getActualTypeArguments();

                if (typeArguments.length > 0 && typeArguments[0] == traitClass) {
                    try {
                        field.setAccessible(true);
                        return Optional.of((Set<T>) field.get(obj));
                    } catch (IllegalAccessException e) {
                        System.out.println(e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return Optional.empty();
    }



}
