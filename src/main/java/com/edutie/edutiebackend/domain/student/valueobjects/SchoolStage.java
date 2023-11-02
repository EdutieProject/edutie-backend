package com.edutie.edutiebackend.domain.student.valueobjects;

import com.edutie.edutiebackend.domain.student.enums.SchoolType;

/**
 * The level student has already gained at school. Involves grade they
 * attend and the type of their school.
 * @param schoolType type of the school
 * @param gradeNumber grade number
 */
public record SchoolStage(SchoolType schoolType, int gradeNumber) {
}
