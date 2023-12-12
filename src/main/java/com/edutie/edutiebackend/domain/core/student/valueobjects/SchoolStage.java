package com.edutie.edutiebackend.domain.core.student.valueobjects;

import com.edutie.edutiebackend.domain.core.student.enums.SchoolType;
import lombok.NonNull;

/**
 * The level student has already gained at school. Involves
 * their grade and the type of school.
 * @param schoolType type of the school
 * @param gradeNumber grade number
 */
public record SchoolStage(@NonNull SchoolType schoolType, int gradeNumber) {
}
