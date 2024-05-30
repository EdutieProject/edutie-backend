package com.edutie.backend.domain.education.educator.errors;

import validation.Error;

public class EducatorError {
    public static Error mustBeOwnerError(Class<?> entityClass) {
        return new Error("EDUCATOR-403", "To change " + entityClass.getSimpleName() + " you must be an author educator.");
    }
}
