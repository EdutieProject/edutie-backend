package com.edutie.backend.domain.education.learningrequirement.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class SubRequirementId extends UuidIdentifier {
    public SubRequirementId() {
        super();
    }

    public SubRequirementId(UUID uuid) {
        super(uuid);
    }
}
