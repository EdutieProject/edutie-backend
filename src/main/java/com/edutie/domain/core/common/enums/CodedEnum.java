package com.edutie.domain.core.common.enums;

/**
 * Enum that can serve the code to be persisted as the code in persistence.
 */
public interface CodedEnum<TCode> {
    TCode getCode();
}
