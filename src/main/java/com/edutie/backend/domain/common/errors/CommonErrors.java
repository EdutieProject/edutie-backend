package com.edutie.backend.domain.common.errors;

import validation.Error;

public class CommonErrors {
    public static Error invalidIdentifier() {
        return new Error(
                "IDENTIFIER-404",
                "The provided identifier did not match any identity."
        );
    }

    public static <T> Error invalidIndex(Class<T> entityClass) {
        return new Error(
                "INDEX-400",
                "Index provided to perform the operation on " + entityClass.getSimpleName() + " was not valid."
        );
    }
}
