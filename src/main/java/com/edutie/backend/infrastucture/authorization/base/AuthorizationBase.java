package com.edutie.backend.infrastucture.authorization.base;

import com.edutie.backend.domain.administration.UserId;
import validation.Result;
import org.springframework.security.oauth2.server.resource.authentication.*;

/**
 * Base authorization interface meant to check whether a user has a given role. Provides an {@code authorize()} function
 * which verifies if the user has a specified role and returns the id of the role profile.
 */
public interface AuthorizationBase {
	/**
	 * Authorize the user by checking if the role profile exists for given role.
	 *
	 * @param userId user id
	 * @return Result of the authentication
	 */
	Result authorize(UserId userId);

	/**
	 * Pre-inject roles if any injectable exist in authentication token.
	 *
	 * @param authentication authentication token
	 */
	void injectRoles(JwtAuthenticationToken authentication);
}
