package com.edutie;

import java.lang.reflect.Field;

public class TestUtils {

    public static void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName); // Get the private field
        field.setAccessible(true); // Allow modification
        field.set(target, value); // Set the new value
    }
}
