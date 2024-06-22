package com.edutie.backend.application.profiles.educator.commands;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.education.educator.enums.EducatorType;
import com.edutie.backend.domain.education.educator.identities.EducatorId;
import lombok.NonNull;

public record ModifyEducatorCommand(
        @NonNull UserId adminUserId,
        @NonNull EducatorId educatorId,
        EducatorType educatorType
) {
}
