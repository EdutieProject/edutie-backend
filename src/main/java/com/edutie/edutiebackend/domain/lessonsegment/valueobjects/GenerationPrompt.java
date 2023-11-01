package com.edutie.edutiebackend.domain.lessonsegment.valueobjects;

import com.edutie.edutiebackend.domain.common.base.ValueObject;

import java.util.Arrays;
import java.util.Iterator;

public final class GenerationPrompt extends ValueObject {
    private String prompt;

    public String get() {
        return prompt;
    }

    public void set(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public Iterator<Object> GetEqualityComponents() {
        return Arrays.stream(new Object[]{prompt}).iterator();
    }
}
