package com.edutie.backend.domain.common.base.identity;

import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public class UuidIdentifier extends Identifier<UUID> implements Serializable {
    public UuidIdentifier(UUID value) {
        super(value);
    }

    public UuidIdentifier() {
        super(UUID.randomUUID());
    }
}
