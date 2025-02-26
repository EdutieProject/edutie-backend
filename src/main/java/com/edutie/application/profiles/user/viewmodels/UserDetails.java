package com.edutie.application.profiles.user.viewmodels;

import com.edutie.domain.core.administration.UserId;

public record UserDetails(
        UserId userId,
        String firstName,
        String lastName,
        String fullName
) {
}
