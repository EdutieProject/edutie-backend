package com.edutie.backend.infrastucture.authorization.errors;

import validation.Error;

public class AuthorizationError {
    public static Error educatorRoleExpected() {
        return new Error("EDUCATOR-AUTHORIZATION-401", "Expected Educator role for this user");
    }
    public static Error studentRoleExpected() {
        return new Error("STUDENT-AUTHORIZATION-401", "Expected Student role for this user");
    }
}
