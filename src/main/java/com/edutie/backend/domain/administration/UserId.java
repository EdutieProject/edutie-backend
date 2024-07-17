package com.edutie.backend.domain.administration;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class UserId extends UuidIdentifier {
    public UserId() {
        super();
    }
    @JsonCreator
    public UserId(UUID uuid) {
        super(uuid);
    }
}
