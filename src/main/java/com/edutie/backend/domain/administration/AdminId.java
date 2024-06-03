package com.edutie.backend.domain.administration;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class AdminId extends UuidIdentifier {
    public AdminId() {
        super();
    }

    public AdminId(UUID uuid) {
        super(uuid);
    }
}
