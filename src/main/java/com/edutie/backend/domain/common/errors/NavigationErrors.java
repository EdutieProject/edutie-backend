package com.edutie.backend.domain.common.errors;

import com.edutie.backend.domain.common.base.NavigableEntityBase;
import validation.Error;

public class NavigationErrors {
    public static <T extends NavigableEntityBase<?, ?>> Error elementNotFound(Class<T> elementClass) {
        return new Error(elementClass.getSimpleName() + "NotFound", "No valid element found during navigation configuration in " + elementClass.getSimpleName());
    }

    public static <T extends NavigableEntityBase<?, ?>> Error invalidParentEntity() {
        return new Error("CommonNavigationParentViolation", "Two navigable entities must be in the same parent category to be linked");
    }
}
