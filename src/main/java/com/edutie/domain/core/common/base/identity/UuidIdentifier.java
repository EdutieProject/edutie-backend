package com.edutie.domain.core.common.base.identity;

import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

/**
 * Base UUID identifier. By extending this class strongly typed IDs can be created.
 */
@MappedSuperclass
public class UuidIdentifier extends Identifier<UUID> {
    public UuidIdentifier(UUID value) {
        super(value);
    }

    public UuidIdentifier() {
        super(UUID.randomUUID());
    }
}
