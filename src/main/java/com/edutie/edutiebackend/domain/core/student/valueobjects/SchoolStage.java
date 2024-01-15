package com.edutie.edutiebackend.domain.core.student.valueobjects;

import com.edutie.edutiebackend.domain.core.student.enums.SchoolType;
import jakarta.annotation.Nullable;
import jakarta.persistence.Embeddable;
import lombok.NonNull;


/**
 * Value object describing
 * @param schoolType type of the school
 * @param gradeNumber grade number
 * @param classSpecialization classSpecialization of the class they attend e.g. (Math-Physics)
 */
@Embeddable
public record SchoolStage(
        @NonNull SchoolType schoolType,
        int gradeNumber,
        @Nullable String classSpecialization) {
    public SchoolStage(SchoolType schoolType, int gradeNumber)
    {
        this(schoolType, gradeNumber, null);
    }
    public SchoolStage()
    {
        this(SchoolType.NO_SCHOOL, 0);
    }
}
