package com.edutie.backend.application.profiles.student;

import com.edutie.backend.application.profiles.student.commands.ChangeStudentProfilePropertiesCommand;
import com.edutie.backend.domain.common.identities.UserId;
import validation.Result;

public interface StudentProfileService {
    Result resetStudentProfile(UserId userId);
    Result changeStudentProfileProperties(ChangeStudentProfilePropertiesCommand command);
}
