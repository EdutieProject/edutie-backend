package com.edutie.edutiebackend.domain.student.valueobjects;

import com.edutie.edutiebackend.domain.common.base.ValueObject;
import com.edutie.edutiebackend.domain.student.enums.SchoolType;

import java.util.Arrays;
import java.util.Iterator;

public class SchoolStage extends ValueObject {
    private SchoolType schoolType;
    public short gradeNumber;

    @Override
    public Iterator<Object> GetEqualityComponents() {
        return Arrays.stream(new Object[]{
                schoolType, gradeNumber
        }).iterator();
    }
}
