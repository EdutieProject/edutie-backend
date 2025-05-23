package com.edutie.domain.core.common;

import validation.Error;

/**
 * Common Domain Errors
 */
public class DomainErrors {
    public static Error invalidIdentifier() {
        return new Error("IDENTIFIER-404", "The provided identifier did not match any identity.");
    }

    public static <T> Error invalidIndex(Class<T> entityClass) {
        return new Error("INDEX-400", "Index provided to perform the operation on " + entityClass.getSimpleName() + " was not valid.");
    }

    public static <T> Error noContent(Class<T> entityClass) {
        return new Error("NO-CONTENT-200", String.format("Operation has been proceeded successfully, but there is no %s to be returned.", entityClass.getSimpleName()));
    }
}
