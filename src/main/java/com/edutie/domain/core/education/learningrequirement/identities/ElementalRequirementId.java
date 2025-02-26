package com.edutie.domain.core.education.learningrequirement.identities;

import com.edutie.domain.core.common.base.identity.UuidIdentifier;
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
