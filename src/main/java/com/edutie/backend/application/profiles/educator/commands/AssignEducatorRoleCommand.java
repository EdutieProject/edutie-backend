package com.edutie.backend.application.profiles.educator.commands;

import com.edutie.backend.domain.administration.UserId;
import lombok.NonNull;

public record AssignEducatorRoleCommand(
        @NonNull UserId adminUserId,
        @NonNull UserId educatorUserId
) {
}
