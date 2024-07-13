package com.edutie.backend.application.profiles.student.commands;

import com.edutie.backend.domain.administration.UserId;
import lombok.NonNull;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;

public record ModifyStudentPropertiesCommand(
        @NonNull UserId studentUserId,
        JsonNullable<LocalDate> studentBirthdate
) {
}
