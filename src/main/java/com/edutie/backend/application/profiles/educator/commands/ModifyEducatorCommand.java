package com.edutie.backend.application.profiles.educator.commands;

import com.edutie.backend.domain.common.identities.AdminId;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.educator.enums.EducatorType;

public record ModifyEducatorCommand(
        AdminId adminId,
        UserId educatorUserId,
        EducatorType educatorType
) {
}
