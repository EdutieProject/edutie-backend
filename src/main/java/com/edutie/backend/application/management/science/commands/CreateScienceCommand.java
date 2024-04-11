package com.edutie.backend.application.management.science.commands;

import com.edutie.backend.domain.common.identities.AdminId;

public record CreateScienceCommand(
        AdminId adminId,
        String scienceName,
        String scienceDescription
) {
}
