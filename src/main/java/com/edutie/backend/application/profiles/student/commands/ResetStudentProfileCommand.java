package com.edutie.backend.application.profiles.student.commands;

import com.edutie.backend.domain.administration.UserId;

public record ResetStudentProfileCommand(
        UserId studentUserId
){
}
