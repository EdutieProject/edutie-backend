package com.edutie.application.profiles.student.commands;

import com.edutie.domain.core.administration.UserId;
import org.openapitools.jackson.nullable.JsonNullable;
import lombok.*;

import java.time.LocalDate;

public record ModifyStudentPropertiesCommand(
		@NonNull UserId studentUserId,
		JsonNullable<LocalDate> studentBirthdate) { }
