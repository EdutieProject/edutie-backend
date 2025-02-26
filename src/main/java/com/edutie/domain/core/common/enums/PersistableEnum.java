package com.edutie.domain.core.common.enums;

/**
 * Enum that can serve the code to be persisted as the code in persistence.
 *
 * @param <E> Type of the code.
 */
public interface PersistableEnum<E> {
    E getCode();
}
