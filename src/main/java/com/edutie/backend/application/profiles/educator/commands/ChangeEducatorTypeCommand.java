package com.edutie.backend.application.profiles.educator.commands;

import com.edutie.backend.domain.common.identities.AdminId;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.educator.enums.EducatorType;
import com.edutie.backend.domain.education.educator.identities.EducatorId;

public record ChangeEducatorTypeCommand(
    UserId updateAdminId,
    EducatorId educatorId,
    EducatorType educatorType
) {
}
