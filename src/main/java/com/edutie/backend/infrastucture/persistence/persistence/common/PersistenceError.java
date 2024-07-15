package com.edutie.backend.infrastucture.persistence.persistence.common;

import com.edutie.backend.domain.common.base.EntityBase;
import validation.Error;

public class PersistenceError {
    public static Error exceptionEncountered(Exception ex) {
        return new Error("PERSISTENCE-EXCEPTION-500", ex.getMessage());
    }

    public static Error notFound(Class<? extends EntityBase<?>> entityClass) {
        return new Error("PERSISTENCE-NOT-FOUND-404", entityClass.getSimpleName() + " was not found in persistence");
    }
}
