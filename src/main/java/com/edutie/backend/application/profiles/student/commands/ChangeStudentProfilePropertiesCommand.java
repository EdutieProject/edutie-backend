package com.edutie.backend.application.profiles.student.commands;

import com.edutie.backend.domain.learner.student.valueobjects.SchoolStage;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;

public record ChangeStudentProfilePropertiesCommand(
        JsonNullable<LocalDate> studentBirthdate,
        SchoolStage studentSchoolStage
) {
}
