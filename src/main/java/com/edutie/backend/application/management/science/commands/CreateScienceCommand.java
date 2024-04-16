package com.edutie.backend.application.management.science.commands;

import com.edutie.backend.domain.administration.UserId;
import lombok.NonNull;

public record CreateScienceCommand(
        @NonNull UserId adminUserId,
        @NonNull String scienceName,
        String scienceDescription
) {
}
