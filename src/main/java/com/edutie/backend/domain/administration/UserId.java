package com.edutie.backend.domain.administration;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class UserId extends UuidIdentifier {
    public UserId() {
        super();
    }

    public UserId(UUID uuid) {
        super(uuid);
    }
}
