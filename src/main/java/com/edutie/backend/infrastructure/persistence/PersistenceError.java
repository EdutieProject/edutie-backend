package com.edutie.backend.infrastructure.persistence;

import com.edutie.backend.domain.common.base.EntityBase;
import lombok.extern.slf4j.Slf4j;
import validation.Error;

/**
 * Common persistence errors.
 */
@Slf4j
public class PersistenceError {
	public static Error exceptionEncountered(Exception ex) {
		log.error(ex.getMessage(), ex);
		return new Error("PERSISTENCE-EXCEPTION-500", ex.getMessage());
	}

	public static Error notFound(Class<? extends EntityBase<?>> entityClass) {
		return new Error("PERSISTENCE-NOT-FOUND-404", entityClass.getSimpleName() + " was not found in persistence");
	}
}
