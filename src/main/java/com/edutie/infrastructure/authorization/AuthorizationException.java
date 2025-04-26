package com.edutie.infrastructure.authorization;

import com.edutie.domain.core.common.persistence.RolePersistence;

public class AuthorizationException extends RuntimeException {
    public <T extends RolePersistence<?, ?>> AuthorizationException(Class<T> rolePersistenceClass) {
        super("The invoked flow must be performed with the authorization using the persistence: " + rolePersistenceClass.getName()
                + "; Either the unexpected error occurred or the API endpoint configuration is not authorized.");
    }
}
