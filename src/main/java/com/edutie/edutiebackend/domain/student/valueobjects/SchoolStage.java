package com.edutie.edutiebackend.domain.student.valueobjects;

import com.edutie.edutiebackend.domain.common.base.ValueObject;
import com.edutie.edutiebackend.domain.student.enums.SchoolType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Iterator;

@AllArgsConstructor
@Getter
public class SchoolStage extends ValueObject {
    private SchoolType schoolType;
    private int gradeNumber;

    @Override
    public Iterator<Object> GetEqualityComponents() {
        return Arrays.stream(new Object[]{schoolType, gradeNumber}).iterator();
    }
}
