package com.edutie.backend.domain.core.student.valueobjects;

import com.edutie.backend.domain.core.student.enums.SchoolType;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;


/**
 * Value object describing
 *
 * @param schoolType          type of the school
 * @param gradeNumber         grade number
 * @param classSpecialization classSpecialization of the class they attend e.g. (Math-Physics)
 */
@Embeddable
public record SchoolStage(
        @Convert(converter = SchoolType.Converter.class)
        SchoolType schoolType,
        int gradeNumber,
        String classSpecialization) {
    public SchoolStage(SchoolType schoolType, int gradeNumber) {
        this(schoolType, gradeNumber, null);
    }

    public SchoolStage() {
        this(SchoolType.UNASSIGNED, 0);
    }
}
