package com.edutie.edutiebackend.domain.core.student.valueobjects;

import com.edutie.edutiebackend.domain.core.student.enums.SchoolType;
import jakarta.annotation.Nullable;
import lombok.NonNull;


/**
 * Value object describing
 * @param schoolType type of the school
 * @param gradeNumber grade number
 * @param classSpecialization classSpecialization of the class they attend e.g. (Math-Physics)
 */
public record SchoolStage(
        @NonNull SchoolType schoolType,
        int gradeNumber,
        @Nullable String classSpecialization) {
    public SchoolStage(SchoolType schoolType, int gradeNumber)
    {
        this(schoolType, gradeNumber, null);
    }
}
