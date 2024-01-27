package com.edutie.edutiebackend.domain.core.shared.errors;

import com.edutie.edutiebackend.domain.core.shared.base.NavigableEntityBase;
import com.edutie.edutiebackend.domain.rule.Error;

public class NavigationErrors {
    public static <T extends NavigableEntityBase<?, ?>> Error elementNotFound(Class<T> elementClass) {
        return new Error(elementClass.getSimpleName() + "NotFound", "No valid element found during navigation configuration");
    }
}
