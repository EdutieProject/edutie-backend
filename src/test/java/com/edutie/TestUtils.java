package com.edutie;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class TestUtils {

    public static void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = ReflectionUtils.findField(target.getClass(), fieldName); // Get the private field
        field.setAccessible(true); // Allow modification
        field.set(target, value); // Set the new value
    }
}
