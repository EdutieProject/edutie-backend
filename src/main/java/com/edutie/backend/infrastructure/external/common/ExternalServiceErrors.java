package com.edutie.backend.infrastructure.external.common;

import validation.Error;

public class ExternalServiceErrors {
    public static Error exceptionEncountered( Class<? extends ExternalService> externalServiceClass, Exception exception) {
        return new validation.Error(
                "EXTERNAL-SERVICE-EXCEPTION-500",
                String.format("Exception occurred in external service infrastructure [%s]. Exception message: %s", externalServiceClass.getSimpleName(), exception.getMessage()));
    }

    public static Error invalidStatus(Class<? extends ExternalService> externalServiceClass, int status, String message) {
        return new Error(
                "EXTERNAL-SERVICE-INVALID-STATUS-" + status,
                String.format("%s received invalid status. Message: %s", externalServiceClass.getSimpleName(), message));
    }
}
