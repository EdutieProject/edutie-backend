package com.edutie.backend.infrastructure.authorization;

import com.edutie.backend.domain.administration.Role;
import validation.Error;

/**
 * Common authorization errors
 */
public class AuthorizationError {
	public static <T extends Role<?>> Error roleExpected(Class<T> roleClass) {
		return new Error(roleClass.getSimpleName().toUpperCase() + "-AUTHORIZATION-403", "Expected " + roleClass.getSimpleName() + " role for this user");
	}
}
