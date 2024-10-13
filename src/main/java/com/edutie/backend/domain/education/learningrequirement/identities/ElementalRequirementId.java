package com.edutie.backend.domain.education.learningrequirement.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class ElementalRequirementId extends UuidIdentifier {
    public ElementalRequirementId() {
        super();
    }

    @JsonCreator
    public ElementalRequirementId(UUID uuid) {
        super(uuid);
    }
}
