package com.edutie.backend.application.profiles.educator.commands;

import com.edutie.backend.domain.common.identities.AdminId;
import com.edutie.backend.domain.common.identities.UserId;

public record AssignEducatorRoleCommand(
        AdminId adminId,
        UserId userId
) {
}
