package com.edutie.backend.domain.personalization.learningresourcedefinition.identities;

import com.edutie.backend.domain.common.base.identity.UuidIdentifier;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class LearningResourceDefinitionId extends UuidIdentifier {
    public LearningResourceDefinitionId() {
        super();
    }

    public LearningResourceDefinitionId(UUID uuid) {
        super(uuid);
    }
}
