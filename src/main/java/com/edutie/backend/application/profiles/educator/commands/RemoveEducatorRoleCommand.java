package com.edutie.backend.application.profiles.educator.commands;

import com.edutie.backend.domain.common.identities.AdminId;
import com.edutie.backend.domain.education.educator.identities.EducatorId;

public record RemoveEducatorRoleCommand(
        AdminId adminId,
        EducatorId educatorId
) {
}
