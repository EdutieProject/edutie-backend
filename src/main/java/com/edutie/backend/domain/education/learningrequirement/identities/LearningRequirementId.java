package com.edutie.backend.domain.education.learningrequirement.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class LearningRequirementId extends UuidIdentifier {
    public LearningRequirementId() {
        super();
    }
    @JsonCreator
    public LearningRequirementId(UUID uuid) {
        super(uuid);
    }
}
