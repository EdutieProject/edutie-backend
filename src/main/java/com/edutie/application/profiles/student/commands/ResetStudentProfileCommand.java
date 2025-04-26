package com.edutie.application.profiles.student.commands;

import com.edutie.domain.core.administration.UserId;
import lombok.*;

public record ResetStudentProfileCommand(
		@NonNull UserId studentUserId) { }
