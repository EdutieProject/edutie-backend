package com.edutie.edutiebackend.domain.lessonsegment.valueobjects;

import com.edutie.edutiebackend.domain.common.base.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Iterator;

@AllArgsConstructor
@Getter
public final class GenerationPrompt extends ValueObject {
    private String prompt;


    @Override
    public Iterator<Object> GetEqualityComponents() {
        return Arrays.stream(new Object[]{prompt}).iterator();
    }
}
