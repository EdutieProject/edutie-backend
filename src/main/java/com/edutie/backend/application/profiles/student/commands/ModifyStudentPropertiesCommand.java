package com.edutie.backend.application.profiles.student.commands;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.learner.student.valueobjects.SchoolStage;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;

public record ModifyStudentPropertiesCommand(
        UserId studentUserId,
        JsonNullable<LocalDate> studentBirthdate,
        SchoolStage studentSchoolStage
) {
}
