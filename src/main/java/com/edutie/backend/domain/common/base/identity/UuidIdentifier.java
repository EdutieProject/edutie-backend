package com.edutie.backend.domain.common.base.identity;

import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public class UuidIdentifier extends Identifier<UUID> {
    public UuidIdentifier(UUID value) {
        super(value);
    }

    public UuidIdentifier() {
        super(UUID.randomUUID());
    }
}
