package com.edutie.backend.domain.core.common.errors;

import com.edutie.backend.domain.core.common.base.NavigableEntityBase;
import validation.Error;

public class NavigationErrors {
    public static <T extends NavigableEntityBase<?, ?>> Error elementNotFound(Class<T> elementClass) {
        return new Error(elementClass.getSimpleName() + "NotFound", "No valid element found during navigation configuration in " + elementClass.getSimpleName());
    }
}
