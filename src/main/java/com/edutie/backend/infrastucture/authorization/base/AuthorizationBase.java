package com.edutie.backend.infrastucture.authorization.base;

import com.edutie.backend.domain.common.identities.UserId;
import validation.Result;

/**
 * Base authorization interface meant to check whether a user has a given role. Provides an {@code authorize()} function
 * which verifies if the user has a specified role and returns the id of the role profile.
 */
@FunctionalInterface
public interface AuthorizationBase {
    Result authorize(UserId userId);
}
