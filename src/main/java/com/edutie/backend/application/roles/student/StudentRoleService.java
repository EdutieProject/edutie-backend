package com.edutie.backend.application.roles.student;

import com.edutie.backend.domain.common.identities.UserId;
import validation.Result;

public interface StudentRoleService {
    Result resetStudentProfile(UserId userId);
}
