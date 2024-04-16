package com.edutie.backend.application.profiles.educator.commands;

import com.edutie.backend.domain.administration.AdminId;
import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import lombok.NonNull;

public record RemoveEducatorRoleCommand(
        @NonNull UserId adminUserId,
        @NonNull EducatorId educatorId
) {
}
