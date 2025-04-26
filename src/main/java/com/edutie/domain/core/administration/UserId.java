package com.edutie.domain.core.administration;

import com.edutie.domain.core.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

/**
 * User Identifier represents the user that is held by the identity provider.
 */
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
