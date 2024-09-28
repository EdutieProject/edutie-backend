package com.edutie.backend.api.common;

import validation.Error;

public class AuthenticationError {
	public static Error invalidAuthentication() {
		return new Error("INVALID-AUTHENTICATION-401", "Request contained no valid authentication. Reach out to system administrator to resolve the issue.");
	}

	public static Error noJwtAuthentication() {
		return new Error("NO-JWT-AUTHENTICATION-401", "Provided authentication could not be resolved as Json Web Token auth. Reach out to system administrator to resolve the issue.");
	}
}
