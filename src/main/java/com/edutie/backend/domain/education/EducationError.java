package com.edutie.backend.domain.education;

import validation.Error;

/**
 * Common education subdomain errors.
 */
public class EducationError {
	public static Error educatorMustBeAuthorError(Class<?> entityClass) {
		return new Error("EDUCATOR-403", "To change " + entityClass.getSimpleName() + " you must be an author educator.");
	}

	public static Error educatorInsufficientPermissions() {
		return new Error("EDUCATOR-TYPE-403", "To perform this operation educator must be of higher rank (type)");
	}
}
