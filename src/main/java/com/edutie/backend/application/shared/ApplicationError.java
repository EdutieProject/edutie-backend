package com.edutie.backend.application.shared;

import validation.Error;

//TODO: error translation or different solution ?
public class ApplicationError {
    public static Error persistenceOperationError() {
        return new Error("PERSISTENCE.404", "Database-related error occurred");
    }
    public static Error noImplementationProvided() {
        return new Error("IMPLEMENTATION.500", "This feature has not been implemented");
    }

    public static Error authorizationError() {
        return new Error("AUTHORIZATION.403", "Provided user does not have permission for this operation.");
    }
}
