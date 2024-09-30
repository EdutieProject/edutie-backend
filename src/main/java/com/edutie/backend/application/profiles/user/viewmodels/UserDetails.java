package com.edutie.backend.application.profiles.user.viewmodels;

import com.edutie.backend.domain.administration.UserId;

public record UserDetails(
        UserId userId,
        String firstName,
        String lastName,
        String fullName
) {
}
