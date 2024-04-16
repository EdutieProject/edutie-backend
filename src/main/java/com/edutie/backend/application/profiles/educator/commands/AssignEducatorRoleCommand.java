package com.edutie.backend.application.profiles.educator.commands;

import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;

public record AssignEducatorRoleCommand(
        AdminId adminId,
        UserId userId
) {
}
