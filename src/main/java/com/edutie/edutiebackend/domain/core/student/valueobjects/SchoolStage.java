package com.edutie.edutiebackend.domain.core.student.valueobjects;

import com.edutie.edutiebackend.domain.core.student.enums.SchoolType;


/**
 * The level student has already gained at school. Involves grade they   //Spoko, a co w przypadku gdy jestem studentem, ale chciałbym się nauczyć zupełnie nowej umiejętności jak prasowanie. Wtedy oceny nie będą brane pod uwagę?
 * attend and the type of their school.
 * @param schoolType type of the school
 * @param gradeNumber grade number
 */
public record SchoolStage(SchoolType schoolType, int gradeNumber) {
}
