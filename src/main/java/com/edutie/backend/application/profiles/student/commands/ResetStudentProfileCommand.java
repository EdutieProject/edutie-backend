package com.edutie.backend.application.profiles.student.commands;

import com.edutie.backend.domain.administration.UserId;
import lombok.*;

public record ResetStudentProfileCommand(
		@NonNull UserId studentUserId) { }
