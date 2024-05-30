package com.edutie.backend.domain.common.errors;

import validation.Error;

public class NavigationErrors {
    public static Error invalidParentEntity() {
        return new Error("NAVIGABLE-409", "Two navigable entities must be in the same parent category to be linked");
    }
}
