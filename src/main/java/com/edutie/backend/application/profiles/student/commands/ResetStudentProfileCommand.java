package com.edutie.backend.application.profiles.student.commands;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.learner.student.identities.StudentId;

public record ResetStudentProfileCommand(
        UserId studentUserId
){
}
