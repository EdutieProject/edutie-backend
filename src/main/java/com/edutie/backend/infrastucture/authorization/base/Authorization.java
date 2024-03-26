package com.edutie.backend.infrastucture.authorization.base;

import com.edutie.backend.domain.common.identities.UserId;
import validation.WrapperResult;

/**
 * Base authorization interface meant to check whether a user has a given role. Provides an {@code authorize()} function
 * which verifies if the user has a specified role and returns the id of the role profile.
 * @param <TRoleId> Identifier of a role profile. It is returned by the authorize function.
 */
@FunctionalInterface
public interface Authorization<TRoleId> {
    WrapperResult<TRoleId> authorize(UserId userId);
}
