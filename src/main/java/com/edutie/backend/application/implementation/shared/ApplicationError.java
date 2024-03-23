package com.edutie.backend.application.implementation.shared;

import validation.Error;

public class ApplicationError {
    public static Error persistenceOperationError() {
        return new Error("PERSISTENCE", "Database-related error occurred");
    }
}
